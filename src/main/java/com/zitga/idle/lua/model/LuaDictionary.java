package com.zitga.idle.lua.model;

import com.zitga.idle.battle.utils.LuaPathUtils;
import org.luaj.vm2.LuaValue;

public class LuaDictionary extends LuaObject {

    public LuaDictionary() {
        super(LuaPathUtils.getLibraryPath("collection"), "Dictionary");
    }

    public LuaDictionary(LuaValue luaBinding) {
        super(luaBinding);
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int count() {
        return invoke("Count").toint();
    }

    public LuaValue get(int key) {
        return invoke("Get", LuaValue.valueOf(key));
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void add(LuaValue key, LuaValue value) {
        invoke("Add", key, value);
    }
}