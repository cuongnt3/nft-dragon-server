package com.zitga.idle.lua.libs.eventManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LuaEventManagerTestCase {
    @Test
    public void test() {
        TestEventManager testEventManager = new TestEventManager();
        Assertions.assertNotNull(testEventManager);

        testEventManager.test();
    }
}
