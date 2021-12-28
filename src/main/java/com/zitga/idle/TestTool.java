package com.zitga.idle;

import com.zitga.bean.BeanContainer;
import com.zitga.idle.battle.model.battle.LuaBattle;
import com.zitga.idle.battle.model.battle.output.LuaBattleResult;
import com.zitga.idle.battle.utils.BattleLogUtils;
import com.zitga.idle.battleInfo.utils.MasteryCsvPathUtils;
import com.zitga.idle.config.game.GameConfig;
import com.zitga.idle.enumeration.common.GameMode;
import com.zitga.idle.enumeration.lua.LuaRunMode;
import com.zitga.idle.lua.service.LuaServiceManager;
import com.zitga.idle.utils.FileService;
import com.zitga.mongo.support.log.LoggerFactoryImpl;
import com.zitga.support.JsonService;
import com.zitga.utils.StringUtils;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.jupiter.api.Assertions;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestTool {

    private static final Logger logger = LoggerFactory.getLogger(TestTool.class);

    private static final int NUMBER_RUN = 1;
    private static final boolean IS_CHECK_CONSISTENT = false;
    private static final boolean IS_WRITE_TO_LOG_FILE = true;

    public static void main(String[] args) throws Exception {
        Configurator.initialize("idle-dragonet", "config/log4j.properties");
        MorphiaLoggerFactory.registerLogger(LoggerFactoryImpl.class);

        startTest();
    }

    private static void startTest() throws Exception {
        BeanContainer beanContainer = new BeanContainer();
        beanContainer.start();

        // prepare components
        LuaServiceManager serviceManager = beanContainer.getComponent(LuaServiceManager.class);
        Assertions.assertNotNull(serviceManager);

        FileService fileService = beanContainer.getComponent(FileService.class);
        Assertions.assertNotNull(fileService);

        GameConfig gameConfig = beanContainer.getComponent(GameConfig.class);
        Assertions.assertNotNull(gameConfig);

        // prepare input
        String masteryCsv = fileService.readCsvFile(MasteryCsvPathUtils.getTestMasteryPath());
        Map<File, String> battleContents = readBattleFile(fileService, gameConfig);

        LuaRunMode runMode = gameConfig.getLuaRunMode();
        Map<File, String> resultSamples;
        if (IS_CHECK_CONSISTENT) {
            resultSamples = calculateBattleResultSamples(serviceManager, runMode, battleContents, masteryCsv);
        }

        long totalLogicRunElapsed = 0;
        Map<File, Long> averageRunTimeByFile = new ConcurrentHashMap<>();

        Instant before = Instant.now();

        logger.info("Running battle test ...");
        for (int i = 0; i < NUMBER_RUN; i++) {
            for (Map.Entry<File, String> entry : battleContents.entrySet()) {
                File file = entry.getKey();
                String content = entry.getValue();

                logger.info("Battle: {}", file.getPath());

                Instant startCreateBattle = Instant.now();
                LuaBattle luaBattle = new LuaBattle(GameMode.TEST);
                luaBattle.parseCsv(content, masteryCsv);
                Instant stopCreateBattle = Instant.now();

                Instant startCalculateBattle = Instant.now();
                LuaBattleResult result = serviceManager.calculateBattleResult(luaBattle);

                JsonService jsonService = beanContainer.getComponent(JsonService.class);
                try {
                    logger.info("[BATTLE LOG]: {}", jsonService.writeValueAsString(BattleLogUtils.createBattleLog(result)));
//                    logger.info("[BATTLE LOG]: {}", result.toShortString(LuaRunMode.TEST));

                } catch (Exception e) {
                    logger.info("[BATTLE LOG]: FAIL");
                }

                Instant stopCalculateBattle = Instant.now();

                if (IS_WRITE_TO_LOG_FILE) {
                    String resultText = result.toString(runMode);
                    if (IS_CHECK_CONSISTENT) {
                        Assertions.assertEquals(resultSamples.get(file), resultText);
                    }

                    logger.info(resultText);
                    printAndWriteToLog(gameConfig, resultText, file.getName());
                }

                long createBattleElapsed = Duration.between(startCreateBattle, stopCreateBattle).toMillis();
                logger.info("Create Battle elapsed: {}ms", createBattleElapsed);

                long calculateBattleElapsed = Duration.between(startCalculateBattle, stopCalculateBattle).toMillis();
                totalLogicRunElapsed += calculateBattleElapsed;
                logger.info("Calculate Battle elapsed: {}ms", calculateBattleElapsed);

                long elapsedByFile = calculateBattleElapsed + averageRunTimeByFile.getOrDefault(file, 0L);
                averageRunTimeByFile.put(file, elapsedByFile);
            }
        }

        Instant after = Instant.now();
        long total = Duration.between(before, after).toMillis();

        logger.info("Total file: {} file(s)", averageRunTimeByFile.size());
        for (Map.Entry<File, Long> entry : averageRunTimeByFile.entrySet()) {
            String fileName = String.format("%20s", entry.getKey().getName());
            logger.info("[{}]: {}ms", fileName, entry.getValue() / NUMBER_RUN);
        }

        logger.info("RUN LOGIC BATTLE: {}s", totalLogicRunElapsed / 1000f);
        logger.info("TOTAL: {}s", total / 1000f);
    }

    private static Map<File, String> calculateBattleResultSamples(LuaServiceManager serviceManager, LuaRunMode runMode,
                                                                  Map<File, String> battleContents, String masteryCsv) {
        logger.info("Calculating battle result samples ...");

        Map<File, String> samples = new ConcurrentHashMap<>();
        for (Map.Entry<File, String> entry : battleContents.entrySet()) {
            File file = entry.getKey();
            String content = entry.getValue();

            logger.info("Battle: {}", file.getPath());

            LuaBattle luaBattle = new LuaBattle(GameMode.TEST);
            luaBattle.parseCsv(content, masteryCsv);

            LuaBattleResult result = serviceManager.calculateBattleResult(luaBattle);
            samples.put(file, result.toShortString(runMode));
        }

        return samples;
    }

    private static Map<File, String> readBattleFile(FileService fileService, GameConfig gameConfig) {
        logger.info("Reading battle files ...");

        String battleTestPath = gameConfig.getCsvPath() + "/test/battle";
        List<File> files = fileService.listFile(battleTestPath);

        Map<File, String> fileContents = new ConcurrentHashMap<>(files.size());
        for (File file : files) {
            String csv = fileService.readFile(file.getPath());
            Assertions.assertFalse(StringUtils.isNullOrEmpty(csv));

            fileContents.put(file, csv);
        }

        return fileContents;
    }

    private static void printAndWriteToLog(GameConfig gameConfig, String resultText, String fileName) throws Exception {
        String folderPath = gameConfig.getCsvPath() + "/test/battle_result/";
        File directory = new File(folderPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        String path = String.format(folderPath + "%s.txt", fileName.substring(0, fileName.length() - 4));
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            writer.write(resultText);
        }
    }
}
