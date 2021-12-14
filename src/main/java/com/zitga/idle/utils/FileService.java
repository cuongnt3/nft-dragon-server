package com.zitga.idle.utils;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.idle.config.game.GameConfig;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@BeanComponent
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final GameConfig gameConfig;

    public FileService(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    // ---------------------------------------- List file ----------------------------------------
    public List<File> listFile(String path) {
        File folder = new File(path);
        return listFileInFolder(folder);
    }

    public List<File> listCsvFile(String path) {
        File folder = new File(gameConfig.getCsvPath() + "/" + path);
        return listFileInFolder(folder);
    }

    public List<File> listFileInFolder(File folder) {
        File[] directories = folder.listFiles(File::exists);

        if (directories == null) {
            throw new RuntimeException("Folder not found at " + folder.getPath());
        }

        List<File> files = new ArrayList<>();
        Collections.addAll(files, directories);

        return files;
    }

    public List<File> listFolder(String path) {
        File folder = new File(gameConfig.getCsvPath() + "/" + path);
        File[] directories = folder.listFiles(File::exists);

        if (directories == null) {
            throw new RuntimeException("Folder not found at " + folder.getPath());
        }

        List<File> files = new ArrayList<>();
        for (File file : directories) {
            if (file.isDirectory()) {
                files.add(file);
            }
        }

        return files;
    }

    // ---------------------------------------- Read file ----------------------------------------
    public String readFile(String path) {
        File file = new File("/" + path);
        return readFileContent(file);
    }

    public String readCsvFile(String path) {
        File file = new File(gameConfig.getCsvPath() + "/" + path);
        return readFileContent(file);
    }

    public String readFileContent(File file) {
        String content = null;

        if (file.exists() && file.isFile()) {
            try {
                content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("File not exist: " + file.getPath());
        }

        return content;
    }
}
