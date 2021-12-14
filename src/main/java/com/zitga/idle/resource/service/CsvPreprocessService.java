package com.zitga.idle.resource.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.idle.resource.model.Reward;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class CsvPreprocessService {

    public Map<Integer, List<Reward>> getMapRewardFromCSV(List<Map<String, String>> dataCSVParsed, String tagId) {
        Map<Integer, List<Reward>> instantRewards = new ConcurrentHashMap<>();
        int key = -1;
        List<Map<String, String>> csvRewardOfStage = new ArrayList<>();
        for (Map<String, String> row : dataCSVParsed) {
            if (row.containsKey(tagId) && !row.get(tagId).equals("")) {
                int keyNext = Integer.parseInt(row.get(tagId));

                if (key == -1) {
                    key = keyNext;
                }

                if (keyNext != key) {
                    instantRewards.put(key, getRewards(csvRewardOfStage));
                    csvRewardOfStage = new ArrayList<>();
                    key = keyNext;
                }
            }
            csvRewardOfStage.add(row);
        }

        if (key != -1) {
            instantRewards.put(key, getRewards(csvRewardOfStage));
        }
        return instantRewards;
    }

    public List<List<Map<String, String>>> getListGroupCSV(List<Map<String, String>> csvParsed, String tag) {
        List<List<Map<String, String>>> result = new ArrayList<>();
        List<Map<String, String>> csvListOfElement = new ArrayList<>();
        int groupId = -1;
        for (Map<String, String> row : csvParsed) {
            if (row.containsKey(tag) && !row.get(tag).equals("")) {
                int groupIdNext = Integer.parseInt(row.get(tag));

                if (groupId == -1) {
                    groupId = groupIdNext;
                }

                if (groupIdNext != groupId) {
                    result.add(csvListOfElement);
                    csvListOfElement = new ArrayList<>();
                }
            }

            csvListOfElement.add(row);
        }

        if (groupId != -1) {
            result.add(csvListOfElement);
        }

        if (result.isEmpty()) {
            throw new RuntimeException("result must not null");
        }

        return result;
    }

    public List<Reward> getRewards(List<Map<String, String>> csvData) {
        List<Reward> result = new ArrayList<>();
        for (Map<String, String> data : csvData) {
            Reward reward = new Reward(data);
            result.add(reward);
        }
        return result;
    }
}
