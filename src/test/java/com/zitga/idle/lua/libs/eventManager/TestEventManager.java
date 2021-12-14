package com.zitga.idle.lua.libs.eventManager;

import com.zitga.idle.lua.model.LuaObject;

public class TestEventManager extends LuaObject {
    public TestEventManager() {
        super("lua/test/libs/event", "TestEventManager");
    }

    public void test() {
        invoke("Test");
    }
}
