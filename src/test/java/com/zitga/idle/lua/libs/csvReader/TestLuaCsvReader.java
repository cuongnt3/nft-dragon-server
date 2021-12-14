package com.zitga.idle.lua.libs.csvReader;

import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class TestLuaCsvReader extends LuaObject {
    public TestLuaCsvReader() {
        super("lua/test/libs", "TestCsvReader");
    }

    public void test(String content) {
        invoke("Test", LuaValue.valueOf(content));
    }
}
