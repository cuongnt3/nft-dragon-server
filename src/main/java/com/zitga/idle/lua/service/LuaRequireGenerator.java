package com.zitga.idle.lua.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.idle.config.game.GameConfig;
import com.zitga.idle.config.lua.LuaLoadRuleConfig;
import com.zitga.idle.config.lua.LuaLoadSequence;
import com.zitga.idle.utils.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

@BeanComponent
public class LuaRequireGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FileService fileService;

    private final GameConfig gameConfig;
    private final LuaLoadRuleConfig loadRuleConfig;

    private final ArrayList<String> preloadFilePaths = new ArrayList<>();

    public LuaRequireGenerator(FileService fileService, GameConfig gameConfig, LuaLoadRuleConfig loadRuleConfig) {
        this.fileService = fileService;

        this.gameConfig = gameConfig;
        this.loadRuleConfig = loadRuleConfig;
    }

    public void start() {
        logger.info("[LUA] Generating static requirements ...");
        String luaPath = gameConfig.getLuaPath() + "/";
        for (LuaLoadSequence sequence : loadRuleConfig.getLoadSequences()) {
            if (sequence.isFolder()) {
                for (String folderPath : sequence.getPaths()) {
                    createPreloadFolderList(luaPath + folderPath);
                }
            } else {
                for (String filePath : sequence.getPaths()) {
                    createPreloadFilePath(filePath);
                }
            }
        }
    }

    public void generateFile() throws Exception {
        String luaRequireFilePath = gameConfig.getLuaPath() + "/" + loadRuleConfig.getLuaNameToGenerate();
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(luaRequireFilePath), StandardCharsets.UTF_8))) {
            writer.write("local req = require");
            writer.newLine();

            for (String item : preloadFilePaths) {
                if (item.length() > 0) {
                    writer.write(String.format("req \"%s\"", item));
                    writer.newLine();
                } else {
                    writer.newLine();
                }
            }
        }

        preloadFilePaths.clear();
    }

    private void createPreloadFolderList(String preloadPath) {
        Collection<File> files = fileService.listFile(preloadPath);
        for (File file : files) {
            String path = file.getPath();

            if (file.isDirectory()) {
                if (!loadRuleConfig.isFolderExcluded(path)) {
                    createPreloadFolderList(path);
                }
            } else {
                if (!loadRuleConfig.isFileExcluded(file.getName())) {
                    path = getLuaFileName(path);

                    if (!preloadFilePaths.contains(path)) {
                        preloadFilePaths.add(path);
                    }
                }
            }
        }
    }

    private void createPreloadFilePath(String path) {
        // Remove path separator
        path = path.replace("\\", ".");

        preloadFilePaths.add(path);
    }

    private String getLuaFileName(String path) {
        // Remove extension
        int index = path.indexOf("\\lua\\");
        path = path.substring(index + 1, path.length() - 4);

        // Remove path separator
        path = path.replace("\\", ".");

        return path;
    }
}