package com.zitga.idle.battle.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.core.message.socket.IDeserializable;
import com.zitga.core.message.socket.ISerializable;
import com.zitga.idle.ServiceController;
import com.zitga.idle.battle.constant.BattleConstant;
import com.zitga.idle.battle.constant.BattleTag;
import com.zitga.idle.lua.model.LuaDictionary;
import io.netty.buffer.ByteBuf;
import org.luaj.vm2.LuaValue;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity(value = "", noClassnameStored = true)
public class PredefineTeamData implements ISerializable, IDeserializable {

    @JsonProperty("0")
    @Property(BattleTag.FORMATION_ID_TAG)
    private int formationId = BattleConstant.NOT_EXISTED_ID;

    @JsonProperty("1")
    @Property(BattleTag.SUMMONER_DEFINE_ID_TAG)
    private int teamSummonerId = BattleConstant.NOT_EXISTED_ID;

    @JsonProperty("2")
    @Property(BattleTag.ID_TEAM_LEVEL_TAG)
    private int teamLevelId = BattleConstant.NOT_EXISTED_ID;

    @JsonProperty("3")
    @Property(BattleTag.ID_TEAM_STAR_TAG)
    private int teamStarId = BattleConstant.NOT_EXISTED_ID;

    @JsonProperty("4")
    @Property(BattleTag.BOSS_SLOT_TAG)
    private int bossSlotId = BattleConstant.NOT_EXISTED_ID;

    @JsonProperty("5")
    @Embedded(BattleTag.HERO_LIST_TAG)
    private final List<Integer> heroList = new ArrayList<>();

    @JsonProperty("6")
    @Property(BattleTag.ID_TEAM_ITEM_TAG)
    private int teamItemId = BattleConstant.NOT_EXISTED_ID;

    @JsonProperty("7")
    @Property(BattleTag.ID_TEAM_MASTERY_TAG)
    private int teamMasteryId = BattleConstant.NOT_EXISTED_ID;

    public PredefineTeamData() {
        // For serialize purpose
    }

    public PredefineTeamData(Map<String, String> data) {
        formationId = Integer.parseInt(data.get(BattleTag.FORMATION_ID_TAG));
        teamSummonerId = Integer.parseInt(data.getOrDefault(BattleTag.SUMMONER_DEFINE_ID_TAG, "-1"));

        teamLevelId = Integer.parseInt(data.get(BattleTag.ID_TEAM_LEVEL_TAG));
        teamStarId = Integer.parseInt(data.get(BattleTag.ID_TEAM_STAR_TAG));

        teamItemId = Integer.parseInt(data.getOrDefault(BattleTag.ID_TEAM_ITEM_TAG, "-1"));
        teamMasteryId = Integer.parseInt(data.getOrDefault(BattleTag.ID_TEAM_MASTERY_TAG, "-1"));

        bossSlotId = Integer.parseInt(data.getOrDefault(BattleTag.BOSS_SLOT_TAG, "-1"));

        for (int i = 0; i < BattleConstant.NUMBER_SLOT; i++) {
            heroList.add(Integer.parseInt(data.get(BattleTag.HERO_SLOT_TAG + (i + 1))));
        }

        ServiceController.instance().getLuaServiceManager().getPredefineTeamDataService().validate(this);
    }

    // ---------------------------------------- Getters ----------------------------------------
    @JsonIgnore
    public int getFormationId() {
        return formationId;
    }

    @JsonIgnore
    public int getTeamSummonerId() {
        return teamSummonerId;
    }

    @JsonIgnore
    public int getTeamLevelId() {
        return teamLevelId;
    }

    @JsonIgnore
    public int getTeamStarId() {
        return teamStarId;
    }

    @JsonIgnore
    public List<Integer> getHeroList() {
        return heroList;
    }

    @JsonIgnore
    public int getBossSlotId() {
        return bossSlotId;
    }

    @JsonIgnore
    public int getTeamItemId() {
        return teamItemId;
    }

    @JsonIgnore
    public int getTeamMasteryId() {
        return teamMasteryId;
    }

    @JsonIgnore
    public LuaValue getLuaHeroDictionary() {
        LuaDictionary heroDict = new LuaDictionary();

        for (int i = 0; i < heroList.size(); i++) {
            heroDict.add(LuaValue.valueOf(i + 1), LuaValue.valueOf(heroList.get(i)));
        }

        return heroDict.toLua();
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeByte(formationId);
        out.writeIntLE(teamSummonerId);

        out.writeIntLE(teamLevelId);
        out.writeIntLE(teamStarId);

        out.writeIntLE(teamItemId);
        out.writeIntLE(teamMasteryId);

        out.writeIntLE(bossSlotId);

        out.writeByte(heroList.size());
        for (int heroId : heroList) {
            out.writeIntLE(heroId);
        }
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setFormationId(int formationId) {
        this.formationId = formationId;
    }

    @Override
    public void deserialize(ByteBuf in) {
        formationId = in.readUnsignedByte();
        teamSummonerId = in.readIntLE();

        teamLevelId = in.readIntLE();
        teamStarId = in.readIntLE();

        teamItemId = in.readIntLE();
        teamMasteryId = in.readIntLE();

        bossSlotId = in.readIntLE();

        int size = in.readUnsignedByte();
        for (int i = 0; i < size; i++) {
            heroList.add(in.readIntLE());
        }
    }
}