package com.zitga.player.service;

import com.zitga.authentication.basicInfo.dao.PlayerBasicInfoDAO;
import com.zitga.authentication.basicInfo.model.PlayerBasicInfo;
import com.zitga.authentication.basicInfo.service.BasicInfoService;
import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.dragon.service.DragonService;
import com.zitga.executor.service.ExecutorService;
import com.zitga.player.model.IAsyncPlayerDataManageable;
import com.zitga.player.model.Player;
import com.zitga.summon.service.SummonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@BeanComponent
public class PlayerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private CachedPlayerService cachedPlayerService;

    @BeanField
    private SummonService summonService;

    @BeanField
    private DragonService dragonService;

    @BeanField
    private ExecutorService executorService;

    @BeanField
    private PlayerBasicInfoDAO basicInfoDAO;

    @BeanField
    private BasicInfoService basicInfoService;

    private List<IAsyncPlayerDataManageable> playerAsyncDataManager;

    @BeanMethod
    private void init() {
        logger.info("Initializing PlayerService ...");

        // manually add async data manager to control order of load
        playerAsyncDataManager = new ArrayList<>();
        playerAsyncDataManager.add(dragonService);
    }

    // ---------------------------------------- Create & Load ----------------------------------------
    public Player loadPlayer(PlayerAuthentication authentication) throws Exception {
        Player player = cachedPlayerService.getPlayer(authentication.getId());
        if (player == null) {

            PlayerBasicInfo basicInfo = basicInfoDAO.findOne(authentication.getId());
            if (basicInfo == null){
                player = createPlayerComponents(authentication);
            }else {
                player = loadPlayerComponents(authentication);
            }
        }

        // only put to cache when everything is loaded
        cachedPlayerService.addToCache(player);
        initPlayerComponents(player, authentication);
        return player;
    }

    //----------------------------------------------------------------------------------
    private Player createPlayerComponents(PlayerAuthentication authentication) throws Exception {
        Player player = new Player(authentication);

        basicInfoService.createBasicInfo(player);

        // create player data async
        CountDownLatch latch = new CountDownLatch(playerAsyncDataManager.size());
        for (IAsyncPlayerDataManageable manager : playerAsyncDataManager) {
            executorService.executePlayerTask(() -> {
                try {
                    manager.createPlayerData(player);
                } catch (Throwable e) {
                    logger.error(e.getMessage(), e);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        logger.debug("[PLAYER] CREATE player = {}", player);
        return player;
    }

    private Player loadPlayerComponents(PlayerAuthentication authentication) throws Exception {
        Player player = new Player(authentication);

        basicInfoService.loadBasicInfo(player);

        // load player data async
        // wait until all loaders is finished
        CountDownLatch latch = new CountDownLatch(playerAsyncDataManager.size());
        for (IAsyncPlayerDataManageable manager : playerAsyncDataManager) {
            executorService.executePlayerTask(() -> {
                try {
                    manager.loadPlayerData(player);
                } catch (Throwable e) {
                    logger.error(e.getMessage(), e);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        logger.debug("[PLAYER] LOAD player = {}", player);
        return player;
    }

    private void initPlayerComponents(Player player, PlayerAuthentication authentication) {
        player.replaceAuth(authentication);
        // TODO
    }
}