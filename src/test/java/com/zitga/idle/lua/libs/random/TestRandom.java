package com.zitga.idle.lua.libs.random;

import com.zitga.idle.lua.model.LuaObject;

public class TestRandom extends LuaObject {
    public TestRandom() {
        super("lua/test/libs", "TestRandom");
    }

    public void test() {
        invoke("Test");
    }
}

