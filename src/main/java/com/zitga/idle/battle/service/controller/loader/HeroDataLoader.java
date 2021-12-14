package com.zitga.idle.battle.service.controller.loader;

import com.zitga.idle.battle.model.hero.LuaHeroDataEntry;
import com.zitga.idle.battle.model.hero.raw.HeroDataEntryRaw;
import com.zitga.idle.battle.service.controller.LuaHeroDataService;
import com.zitga.idle.hero.service.HeroDataService;
import com.zitga.idle.hero.utils.HeroCsvPathUtils;
import com.zitga.idle.utils.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeroDataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FileService fileService;
    private final HeroDataService heroDataService;

    public HeroDataLoader(FileService fileService, HeroDataService heroDataService) {
        this.fileService = fileService;
        this.heroDataService = heroDataService;
    }

    public void load(LuaHeroDataService luaHeroDataService) {
        // Load CSV
        Map<Integer, HeroDataEntryRaw> entryRawList = loadHeroEntryRaw();

        // Create hero data
        for (Map.Entry<Integer, HeroDataEntryRaw> heroData : entryRawList.entrySet()) {
            int heroId = heroData.getKey();
            heroDataService.addHeroId(heroId);

            logger.trace("Loading hero {} ...", heroId);
            HeroDataEntryRaw rawData = heroData.getValue();

            LuaHeroDataEntry entry = new LuaHeroDataEntry(heroId);
            entry.parseCsv(rawData.getStatData());

            String[] skillDataList = rawData.getSkillDataList();
            for (int i = 0; i < skillDataList.length; i++) {
                entry.addSkillData(i + 1, skillDataList[i]);
            }

            heroDataService.addHeroClass(heroId, entry.getHeroClass());

            entry.validate();
            luaHeroDataService.addHeroData(entry.toLua());
        }
    }

    private Map<Integer, HeroDataEntryRaw> loadHeroEntryRaw() {
        Map<Integer, HeroDataEntryRaw> result = new ConcurrentHashMap<>();

        List<File> heroFolders = getAllHeroFiles();
        for (File folder : heroFolders) {
            String fileName = folder.getName();

            int heroId = parseHeroId(fileName);
            if (heroId == -1) {
                continue;
            }

            if (result.containsKey(heroId)) {
                logger.warn("Hero Id is duplicated: fileName={}", fileName);
                continue;
            }

            List<File> heroFiles = fileService.listFileInFolder(folder);
            heroFiles.sort((first, second) -> first.getName().compareToIgnoreCase(second.getName()));

            String statData = fileService.readFileContent(heroFiles.get(0));

            HeroDataEntryRaw entryRaw = new HeroDataEntryRaw(statData);
            for (int i = 1; i < heroFiles.size(); i++) {
                File heroFile = heroFiles.get(i);

                String skillData = fileService.readFileContent(heroFile);
                if (skillData != null) {
                    int skillId = parseSkillId(heroFile.getName());
                    entryRaw.addSkillData(skillId - 1, skillData);
                }
            }

            result.put(heroId, entryRaw);
        }

        return result;
    }

    private List<File> getAllHeroFiles() {
        List<File> result = new ArrayList<>();

        List<File> folders = fileService.listCsvFile(HeroCsvPathUtils.getHeroPath());
        for (File folder : folders) {
            if (folder.isDirectory()) {
                List<File> files = fileService.listFileInFolder(folder);
                result.addAll(files);
            }
        }

        return result;
    }

    private int parseHeroId(String fileName) {
        int heroId = -1;

        String[] parts = fileName.split("_");
        if (parts.length != 3) {
            return heroId;
        }

        try {
            heroId = Integer.parseInt(parts[1]);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return heroId;
    }

    private int parseSkillId(String fileName) {
        int skillId = -1;

        String[] parts = fileName.split("_");
        if (parts.length != 3) {
            logger.warn("Skill file name is in bad format: fileName={}", fileName);
            return skillId;
        }

        try {
            String[] nameParts = parts[2].split("\\.");
            skillId = Integer.parseInt(nameParts[0]);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return skillId;
    }
}
