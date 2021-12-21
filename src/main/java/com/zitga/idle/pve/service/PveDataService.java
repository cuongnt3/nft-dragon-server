package com.zitga.idle.pve.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.battle.model.data.PredefineTeamData;
import com.zitga.idle.campaign.utils.CampaignCsvPathUtils;
import com.zitga.idle.campaign.utils.CampaignUtils;
import com.zitga.idle.utils.FileService;
import com.zitga.support.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class PveDataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private CsvService csvService;

    @BeanField
    private FileService fileService;

    // key: stage id
    private Map<Integer, PredefineTeamData> defenderTeam;
    private int maxStage;
    private List<Integer> campaignStageList;

    @BeanMethod
    private void init(){
        logger.info("Initializing PveDataService ...");

        createDefenderTeamData();
    }

    private void createDefenderTeamData() {
        String csvString = fileService.readCsvFile(CampaignCsvPathUtils.getCampaignDefenderTeamPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        defenderTeam = new ConcurrentHashMap<>();
        campaignStageList = new ArrayList<>();

        for (Map<String, String> data : csvData) {
            int key = Integer.parseInt(data.get(BasicTag.STAGE_TAG));
            PredefineTeamData predefineTeamData = new PredefineTeamData(data);
            defenderTeam.put(key, predefineTeamData);
            campaignStageList.add(key);

            maxStage = Math.max(maxStage, key);
        }
        campaignStageList.sort(null);
    }

    public PredefineTeamData getDefenderData(int stage) {
        if (defenderTeam.containsKey(stage)) {
            return defenderTeam.get(stage);
        }
        return null;
    }
}
