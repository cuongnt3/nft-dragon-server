package com.zitga.idle.lua.model;

import com.zitga.idle.battle.utils.LuaPathUtils;
import org.luaj.vm2.LuaValue;

public class LuaList extends LuaObject {

    public LuaList() {
        super(LuaPathUtils.getLibraryPath("collection"), "List");
    }

    public LuaList(LuaValue luaBinding) {
        super(luaBinding);
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int count() {
        return invoke("Count").toint();
    }

    public LuaValue get(int index) {
        return invoke("Get", LuaValue.valueOf(index));
    }

    // ---------------------------------------- Setters ----------------------------------------
}