package com.zitga.idle.lua.function;

import com.zitga.idle.lua.utils.LuaUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.luaj.vm2.LuaValue;

public class LuaFunctionTestCase {

    @Test
    public void test() {
        LuaUtils.loadModule("lua/test/lua/TestFunction.lua");

        LuaValue testFunction = LuaUtils.getModule("TestFunction");
        Assertions.assertNotNull(testFunction);

        int result = LuaUtils.invoke(testFunction, "NoParam").toint();
        Assertions.assertEquals(result, 0);

        result = LuaUtils.invoke(testFunction, "OneParam",
                LuaValue.valueOf(1)).toint();
        Assertions.assertEquals(result, 1);

        result = LuaUtils.invoke(testFunction, "TwoParam",
                LuaValue.valueOf(1), LuaValue.valueOf(2)).toint();
        Assertions.assertEquals(result, 1 + 2);

        result = LuaUtils.invoke(testFunction, "ThreeParam",
                LuaValue.valueOf(1), LuaValue.valueOf(2), LuaValue.valueOf(3)).toint();
        Assertions.assertEquals(result, 1 + 2 + 3);
    }
}
