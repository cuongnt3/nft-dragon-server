package com.zitga.idle.lua.libs.random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LuaRandomTestCase {
    @Test
    public void test() {
        TestRandom testRandom = new TestRandom();
        Assertions.assertNotNull(testRandom);

        testRandom.test();
    }
}
