package com.zitga.idle.campaign.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.base.constant.CollectionName;
import com.zitga.idle.base.model.BasePlayerComponent;
import com.zitga.idle.campaign.constant.CampaignConstant;
import com.zitga.idle.campaign.constant.CampaignTag;
import com.zitga.idle.player.model.Player;
import com.zitga.utils.TimeUtils;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = CollectionName.PLAYER_CAMPAIGN, noClassnameStored = true)
public class PlayerCampaign extends BasePlayerComponent {

    @JsonIgnore
    @Version
    @Property(BasicTag.DOCUMENT_VERSION_TAG)
    private long documentVersion;

    @JsonProperty("0")
    @Property(BasicTag.STAGE_TAG)
    // Stage Max Passed
    private int stage;

    @JsonProperty("1")
    @Property(CampaignTag.STAGE_IDLE_TAG)
    // Stage Idle Current
    private int stageIdle;

    @JsonProperty("7")
    @Embedded(CampaignTag.SLOT_TRAINING_EXP)
    private List<Long> trainingHeroes;

    @JsonProperty("8")
    @Property(CampaignTag.HIGHEST_STAGE_TIME_TAG)
    private long highestStageTime;

    @JsonProperty("9")
    @Property(CampaignTag.QUICK_BATTLE_BUY_TURN_TAG)
    private int quickBattleBuyTurn;

    @JsonProperty("10")
    @Embedded(CampaignTag.QUICK_BATTLE_TICKET_TAG)
    // key: ticket id, value: number ticket
    private Map<Integer, Integer> quickBattleTicketMap;

    public PlayerCampaign() {
        // For serialize purpose
        initWithDefaultValues();
    }

    public PlayerCampaign(Player player) {
        super(player);
        initWithDefaultValues();
    }

    public void initWithDefaultValues() {
        stage = CampaignConstant.NO_STAGE;
        stageIdle = CampaignConstant.NO_STAGE;

        trainingHeroes = new ArrayList<>();

        highestStageTime = TimeUtils.getCurrentTimeInSecond();

        quickBattleBuyTurn = 1;
        quickBattleTicketMap = new ConcurrentHashMap<>();
    }

    // ---------------------------------------- Getters ----------------------------------------
    @JsonIgnore
    public int getStage() {
        return stage;
    }

    @JsonIgnore
    public int getStageIdle() {
        return stageIdle;
    }

    @JsonIgnore
    public List<Long> getTrainingHeroes() {
        return trainingHeroes;
    }

    @JsonIgnore
    public int getQuickBattleBuyTurn() {
        return quickBattleBuyTurn;
    }

    @JsonIgnore
    public int getNumberQuickBattleTicket(int id) {
        return quickBattleTicketMap.getOrDefault(id, 0);
    }

    // ---------------------------------------- Setters ----------------------------------------


    public void setStageIdle(int stageIdle) {
        this.stageIdle = stageIdle;
    }

    public void setStage(int newStage) {
        if (newStage > stage) {
            highestStageTime = TimeUtils.getCurrentTimeInSecond();
        }
        stage = newStage;
    }

    public void setTrainList(List<Long> heroInventoryIds) {
        this.trainingHeroes = heroInventoryIds;
    }

    public void clearTrainList() {
        this.trainingHeroes.clear();
    }

    public void resetNumberQuickBattleBuy() {
        quickBattleBuyTurn = 1;
    }

    public void increaseNumberQuickBattleBuy(int numberBuy) {
        quickBattleBuyTurn += numberBuy;
    }

    public void addQuickBattleTicket(int ticketId, int number) {
        if (ticketId > 0) {
            int currentNumber = quickBattleTicketMap.getOrDefault(ticketId, 0);
            quickBattleTicketMap.put(ticketId, currentNumber + number);
        }
    }

    public void removeQuickBattleTicket(int ticketId, int number) {
        if (ticketId > 0) {
            int currentNumber = quickBattleTicketMap.getOrDefault(ticketId, 0);
            if (currentNumber <= number) {
                quickBattleTicketMap.put(ticketId, 0);
            } else {
                quickBattleTicketMap.put(ticketId, currentNumber - number);
            }
        }
    }
}