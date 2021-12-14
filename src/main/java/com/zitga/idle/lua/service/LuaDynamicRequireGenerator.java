package com.zitga.idle.lua.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.config.game.GameConfig;
import com.zitga.idle.config.lua.LuaLoadRuleConfig;
import com.zitga.idle.utils.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class LuaDynamicRequireGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private final LuaServiceManager luaServiceManager;

    @BeanField
    private final FileService fileService;

    @BeanField
    private final GameConfig gameConfig;

    @BeanField
    private final LuaLoadRuleConfig loadRuleConfig;

    private final Map<Integer, List<String>> dynamicFilePaths = new ConcurrentHashMap<>();

    public LuaDynamicRequireGenerator(LuaServiceManager luaServiceManager, FileService fileService,
                                      GameConfig gameConfig, LuaLoadRuleConfig loadRuleConfig) {
        this.luaServiceManager = luaServiceManager;
        this.fileService = fileService;

        this.gameConfig = gameConfig;
        this.loadRuleConfig = loadRuleConfig;
    }

    public void start() {
        logger.info("[LUA] Generating dynamic requirements ...");
        String luaPath = gameConfig.getLuaPath() + "/";
        for (String path : loadRuleConfig.getDynamicLoadRule().getPaths()) {
            createDynamicFileList(luaPath + path);
        }

        logger.info("[LUA] Loading dynamic requirements ...");
        for (List<String> paths : dynamicFilePaths.values()) {
            for (String path : paths) {
                luaServiceManager.loadDynamicRequire(path);
            }
        }
    }

    private void createDynamicFileList(String preloadPath) {
        Collection<File> files = fileService.listFile(preloadPath);
        for (File file : files) {
            if (file.isDirectory()) {
                String folderName = file.getName();
                if (!loadRuleConfig.getDynamicLoadRule().isExclude(folderName)) {
                    int heroId = Integer.parseInt(folderName.substring(folderName.length() - 5));

                    List<String> dynamicFileList = getOrCreateDynamicList(heroId);

                    Collection<File> heroFiles = fileService.listFile(file.getPath());
                    for (File heroFile : heroFiles) {
                        String path = heroFile.getPath();
                        path = getLuaFileName(path);

                        dynamicFileList.add(path);
                    }
                }
            }
        }
    }

    private String getLuaFileName(String path) {
        // Remove extension
        int index = path.indexOf("\\lua\\");
        path = path.substring(index + 1, path.length() - 4);

        // Remove path separator
        path = path.replace("\\", ".");

        return path;
    }

    private List<String> getOrCreateDynamicList(int id) {
        return dynamicFilePaths.computeIfAbsent(id, k -> new ArrayList<>());
    }
}
