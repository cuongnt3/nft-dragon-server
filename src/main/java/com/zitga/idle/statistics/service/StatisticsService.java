package com.zitga.idle.statistics.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.player.model.IAsyncPlayerDataManageable;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.statistics.dao.PlayerStatisticsDAO;
import com.zitga.idle.statistics.model.PlayerStatistics;
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