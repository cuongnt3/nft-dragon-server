package com.zitga.idle.lua.service;

import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.lua.model.LuaObject;
import com.zitga.utils.RandomUtils;
import org.luaj.vm2.LuaValue;

public class LuaRandomHelper extends LuaObject {

    private int seed;

    public LuaRandomHelper() {
        super(LuaPathUtils.getLibraryPath(""), "RandomHelper");
        this.randomSeed();
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getSeed() {
        return seed;
    }

    public int getNumberRandom() {
        return invoke("GetNumberRandom").toint();
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void randomSeed() {
        this.seed = RandomUtils.nextInt(1, 999999999);
        invoke("SetSeed", LuaValue.valueOf(seed));
    }
}