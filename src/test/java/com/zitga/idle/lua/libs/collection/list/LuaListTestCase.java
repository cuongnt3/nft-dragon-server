package com.zitga.idle.lua.libs.collection.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LuaListTestCase {
    @Test
    public void test() {
        TestLuaList testLuaList = new TestLuaList();
        Assertions.assertNotNull(testLuaList);

        testLuaList.test();
    }
}
