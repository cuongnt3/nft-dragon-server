package com.zitga.idle.battleInfo.model.summoner;

import com.zitga.core.message.socket.IDeserializable;
import com.zitga.core.message.socket.ISerializable;
import com.zitga.idle.battleInfo.constant.BattleInfoConstant;
import com.zitga.idle.battleInfo.constant.BattleInfoTag;
import com.zitga.idle.hero.constant.HeroConstant;
import io.netty.buffer.ByteBuf;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = "", noClassnameStored = true)
public class SummonerSkill implements ISerializable, IDeserializable {

    @Embedded(BattleInfoTag.SUMMONER_SKILL_TIER)
    // key: skillId, value: selected skill tier
    private Map<Integer, Integer> skillTiers = new ConcurrentHashMap<>();

    public SummonerSkill() {
        for (int i = 1; i <= HeroConstant.NUMBER_SKILL; i++) {
            skillTiers.put(i, 1);
        }
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getSkillLevel(int star, int skillId) {
        int selectedTier = skillTiers.get(skillId);

        switch (selectedTier) {
            case 1:
                return Math.min(star, BattleInfoConstant.SUMMONER_SKILL_TIER_1_MAX_LEVEL);

            case 2:
                return Math.min(star, BattleInfoConstant.SUMMONER_SKILL_TIER_2_MAX_LEVEL);

            case 3:
                return Math.min(star, BattleInfoConstant.SUMMONER_SKILL_TIER_3_MAX_LEVEL);
        }

        return Math.min(star, BattleInfoConstant.SUMMONER_SKILL_TIER_1_MAX_LEVEL);
    }

    public List<Integer> getSkills() {
        return new ArrayList<>(skillTiers.values());
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeByte(skillTiers.size());

        for (Map.Entry<Integer, Integer> entry : skillTiers.entrySet()) {
            out.writeByte(entry.getKey());
            out.writeByte(entry.getValue());
        }
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void selectSkillTier(int skillId, int skillTier) {
        if (skillTiers.containsKey(skillId)) {
            skillTiers.put(skillId, skillTier);
        }
    }

    public void selectHigherTierSkill(int star) {
        int tier;
        if (star > BattleInfoConstant.SUMMONER_SKILL_TIER_2_MAX_LEVEL) {
            tier = 3;
        } else if (star > BattleInfoConstant.SUMMONER_SKILL_TIER_1_MAX_LEVEL) {
            tier = 2;
        } else {
            tier = 1;
        }

        for (int i = 1; i <= HeroConstant.NUMBER_SKILL; i++) {
            skillTiers.put(i, tier);
        }
    }

    @Override
    public void deserialize(ByteBuf in) {
        int size = in.readUnsignedByte();

        skillTiers = new ConcurrentHashMap<>();
        for (int i = 0; i < size; i++) {
            int skillId = in.readUnsignedByte();
            int skillTier = in.readUnsignedByte();

            skillTiers.put(skillId, skillTier);
        }
    }
}
