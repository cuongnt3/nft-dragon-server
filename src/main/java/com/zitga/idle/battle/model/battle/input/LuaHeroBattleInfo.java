package com.zitga.idle.battle.model.battle.input;

import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.dragon.model.InventoryDragon;
import com.zitga.idle.enumeration.hero.HeroItemSlot;
import com.zitga.idle.hero.model.InventoryHero;
import com.zitga.idle.lua.model.LuaDictionary;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class LuaHeroBattleInfo extends LuaObject {

    public LuaHeroBattleInfo() {
        super(LuaPathUtils.getBattleDataPath(), "HeroBattleInfo");
    }

    public LuaHeroBattleInfo(LuaValue luaBinding) {
        super(luaBinding);
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getHeroId() {
        return getField("heroId").toint();
    }

    public boolean isFrontLine() {
        return getField("isFrontLine").toboolean();
    }

    public int getPosition() {
        return getField("position").toint();
    }

    public int getLevel() {
        return getField("level").toint();
    }

    public int getStar() {
        return getField("star").toint();
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setInfo(int teamId, InventoryDragon inventoryHero) {
        invoke("SetInfo",
                LuaValue.valueOf(teamId),
                LuaValue.valueOf(inventoryHero.getDragonId()),
                LuaValue.valueOf(inventoryHero.getStar()),
                LuaValue.valueOf(inventoryHero.getLevel()));
    }

    public void setInfo(int teamId, int hero, int star, int level) {
        invoke("SetInfo",
                LuaValue.valueOf(teamId),
                LuaValue.valueOf(hero),
                LuaValue.valueOf(star),
                LuaValue.valueOf(level));
    }

    public void setPosition(boolean isFrontLine, int position) {
        invoke("SetPosition", LuaValue.valueOf(isFrontLine), LuaValue.valueOf(position));
    }

    public void setItems(InventoryDragon inventoryHero) {
        LuaDictionary itemDict = new LuaDictionary();

        for (HeroItemSlot slot : HeroItemSlot.values()) {
            LuaValue key = LuaValue.valueOf(slot.getValue());
            LuaValue value = LuaValue.valueOf(inventoryHero.getItem(slot));

            itemDict.add(key, value);
        }

        invoke("SetItemsDict", itemDict.toLua());
    }

    public void setState(double hpPercent, int power) {
        invoke("SetState", LuaValue.valueOf(hpPercent), LuaValue.valueOf(power));
    }
}
