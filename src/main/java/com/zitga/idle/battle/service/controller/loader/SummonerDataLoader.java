package com.zitga.idle.battle.service.controller.loader;

import com.zitga.idle.battle.model.hero.LuaSummonerDataEntry;
import com.zitga.idle.battle.model.hero.LuaSummonerNoviceDataEntry;
import com.zitga.idle.battle.model.hero.raw.SummonerDataEntryRaw;
import com.zitga.idle.battle.service.controller.LuaHeroDataService;
import com.zitga.idle.battleInfo.constant.BattleInfoConstant;
import com.zitga.idle.battleInfo.utils.MasteryCsvPathUtils;
import com.zitga.idle.battleInfo.utils.SummonerCsvPathUtils;
import com.zitga.idle.enumeration.hero.HeroClassType;
import com.zitga.idle.hero.constant.HeroConstant;
import com.zitga.idle.utils.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SummonerDataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FileService fileService;

    public SummonerDataLoader(FileService fileService) {
        this.fileService = fileService;
    }

    public void load(LuaHeroDataService luaHeroDataService) {
        // Load CSV
        Map<HeroClassType, SummonerDataEntryRaw> entryRawMap = loadSummonerEntryRaw();

        // Create summoner data
        for (Map.Entry<HeroClassType, SummonerDataEntryRaw> heroData : entryRawMap.entrySet()) {
            HeroClassType classType = heroData.getKey();
            SummonerDataEntryRaw rawData = heroData.getValue();

            LuaSummonerDataEntry entry = new LuaSummonerDataEntry(classType);
            entry.parseCsv(rawData.getStatData());

            for (int i = 1; i <= HeroConstant.NUMBER_SKILL; i++) {
                entry.addSkillData(i, rawData.getSkillData(i));
            }

            entry.addMastery(rawData.getSummonerMasteries());

            entry.validate();
            luaHeroDataService.addSummonerData(entry.toLua());
        }

        // Create summoner novice data
        String noviceStatData = fileService.readCsvFile(SummonerCsvPathUtils.getSummonerNoviceStatPath());
        String noviceSkillData = fileService.readCsvFile(SummonerCsvPathUtils.getSummonerNoviceSkillPath());

        LuaSummonerNoviceDataEntry noviceDataEntry = new LuaSummonerNoviceDataEntry();
        noviceDataEntry.parseCsv(noviceStatData);
        noviceDataEntry.addSkillData(noviceSkillData);
        noviceDataEntry.validate();
        luaHeroDataService.addSummonerData(noviceDataEntry.toLua());
    }

    private Map<HeroClassType, SummonerDataEntryRaw> loadSummonerEntryRaw() {
        Map<HeroClassType, SummonerDataEntryRaw> result = new ConcurrentHashMap<>();

        for (HeroClassType classType : HeroClassType.values()) {
            String statData = fileService.readCsvFile(SummonerCsvPathUtils.getSummonerStatPath(classType));
            SummonerDataEntryRaw entryRaw = new SummonerDataEntryRaw(statData);

            for (int i = 1; i <= HeroConstant.NUMBER_SKILL; i++) {
                String[] skillTiers = getSkillTiers(classType, i);
                entryRaw.addSkillData(i, skillTiers);
            }

            List<String> masteries = getMasteries(classType);
            entryRaw.setSummonerMasteries(masteries);

            result.put(classType, entryRaw);
        }

        return result;
    }

    private String[] getSkillTiers(HeroClassType classType, int skillId) {
        String[] result = new String[BattleInfoConstant.MAX_SKILL_TIER];

        List<File> files = fileService.listCsvFile(SummonerCsvPathUtils.getSummonerSkillPath(classType, skillId));
        for (File file : files) {
            String content = fileService.readFileContent(file);
            int tier = parseSkillTier(file.getName());

            result[tier - 1] = content;
        }

        return result;
    }

    private List<String> getMasteries(HeroClassType classType) {
        List<String> result = new ArrayList<>();

        List<File> files = fileService.listCsvFile(MasteryCsvPathUtils.getMasteryPath(classType));
        for (File file : files) {
            String content = fileService.readFileContent(file);

            result.add(content);
        }

        return result;
    }

    private int parseSkillTier(String fileName) {
        int tier = -1;

        String[] parts = fileName.split("_");
        if (parts.length != 2) {
            logger.warn("Skill file name is in bad format: fileName={}", fileName);
            return tier;
        }

        try {
            String[] nameParts = parts[1].split("\\.");
            tier = Integer.parseInt(nameParts[0]);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return tier;
    }
}
