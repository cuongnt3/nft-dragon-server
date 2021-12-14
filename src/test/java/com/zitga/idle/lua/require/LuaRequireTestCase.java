package com.zitga.idle.lua.require;

import com.zitga.idle.lua.utils.LuaUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.luaj.vm2.LuaValue;

public class LuaRequireTestCase {

    @Test
    public void test() {
        LuaUtils.loadModule("lua/test/lua/TestRequire.lua");

        LuaValue testRequire = LuaUtils.getModule("TestRequire");
        Assertions.assertNotNull(testRequire);

        LuaValue value = LuaUtils.invoke(testRequire, "Test", LuaValue.valueOf(2), LuaValue.valueOf(3));
        Assertions.assertNotNull(value);

        int result = value.toint();
        Assertions.assertEquals(result, 2 + 3);
    }
}