package com.zitga.idle.lua.object;

import com.zitga.idle.lua.model.LuaObject;

public class TestLuaChildObject extends LuaObject {
    public TestLuaChildObject() {
        super("lua/test/lua", "TestInheritance");
    }

    public int testChildMethod() {
        return invoke("TestChildMethod").toint();
    }

    public int testParentMethod() {
        return invoke("TestParentMethod").toint();
    }
}
