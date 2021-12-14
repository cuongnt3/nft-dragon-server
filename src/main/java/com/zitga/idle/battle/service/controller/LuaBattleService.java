package com.zitga.idle.battle.service.controller;

import com.zitga.idle.battle.model.battle.LuaBattle;
import com.zitga.idle.battle.model.battle.output.LuaBattleResult;
import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.config.game.GameConfig;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class LuaBattleService extends LuaObject {

    private final LuaValue luaRunMode;

    public LuaBattleService(GameConfig gameConfig) {
        super(LuaPathUtils.getServicePath(), "BattleService");
        this.luaRunMode = LuaValue.valueOf(gameConfig.getLuaRunMode().getValue());
    }

    public LuaBattleResult calculateBattleResult(LuaBattle luaBattle) {
        LuaValue value = invoke("CalculateBattleResult", luaBattle.toLua(), luaRunMode);
        return new LuaBattleResult(value, luaBattle.getRandomHelper());
    }
}
