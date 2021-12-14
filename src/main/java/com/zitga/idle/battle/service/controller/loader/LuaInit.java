package com.zitga.idle.battle.service.controller.loader;

import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.lua.model.LuaObject;

public class LuaInit extends LuaObject {

    public LuaInit() {
        super(LuaPathUtils.getLuaPath(), "LuaInit");
    }
}
