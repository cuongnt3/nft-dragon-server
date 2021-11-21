package com.zitga.statistics.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.player.model.IAsyncPlayerDataManageable;
import com.zitga.player.model.Player;
import com.zitga.statistics.dao.PlayerStatisticsDAO;
import com.zitga.statistics.model.PlayerStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BeanComponent
public class StatisticsService implements IAsyncPlayerDataManageable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private PlayerStatisticsDAO statisticsDAO;

    @Override
    public void createPlayerData(Player player) {
        PlayerStatistics playerStatistics = statisticsDAO.create(player);
        player.setStatistics(playerStatistics);
    }

    @Override
    public void loadPlayerData(Player player) {
        PlayerStatistics playerStatistics = statisticsDAO.findOrCreate(player);
        player.setStatistics(playerStatistics);
    }

    public void saveStatistics(Player player) {
        statisticsDAO.save(player.getStatistics());
    }
}