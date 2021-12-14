package com.zitga.idle.lua.libs.collection.list;

import com.zitga.idle.lua.model.LuaObject;

public class TestLuaList extends LuaObject {
    public TestLuaList() {
        super("lua/test/libs/collection", "TestList");
    }

    public void test() {
        invoke("Test");
    }
}
