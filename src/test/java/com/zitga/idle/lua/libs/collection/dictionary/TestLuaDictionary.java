package com.zitga.idle.lua.libs.collection.dictionary;

import com.zitga.idle.lua.model.LuaObject;

public class TestLuaDictionary extends LuaObject {
    public TestLuaDictionary() {
        super("lua/test/libs/collection", "TestDictionary");
    }

    public void test() {
        invoke("Test");
    }
}
