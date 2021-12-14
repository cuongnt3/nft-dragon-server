package com.zitga.idle.lua.object;

import com.zitga.idle.Main;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public class LuaOOPTestCase {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    static {
        try {
            Configurator.initialize("idle-summoner", "config/log4j.properties");
        } catch (Exception e) {
            LoggerFactory.getLogger(Main.class).error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreateObject() {
        // Test object
        TestLuaObject testOOP = new TestLuaObject();

        testOOP.setArea(10);
        int area = testOOP.getArea();
        Assertions.assertEquals(area, 10);

        int noParam = testOOP.noParam();
        Assertions.assertEquals(noParam, 0);

        int oneParam = testOOP.oneParam(1);
        Assertions.assertEquals(oneParam, 1);

        int twoParam = testOOP.twoParam(1, 2);
        Assertions.assertEquals(twoParam, 1 + 2);

        int threeParam = testOOP.threeParam(1, 2, 3);
        Assertions.assertEquals(threeParam, 1 + 2 + 3);

        testOOP.setDummyField(5);
        int dummyField = testOOP.getDummyField();
        Assertions.assertEquals(dummyField, 5);

        float floatParam = testOOP.floatParam(0.000001f);
        Assertions.assertEquals(floatParam, 0.000001f);

        // Test inheritance
        TestLuaChildObject testLuaChildObject = new TestLuaChildObject();
        int childResult = testLuaChildObject.testChildMethod();
        Assertions.assertEquals(childResult, 1);

        childResult = testLuaChildObject.testParentMethod();
        Assertions.assertEquals(childResult, 2);
    }

    @Test
    public void testBenchMark() {
        logger.info("Benchmark Create object");
        for (int i = 0; i < 10; i++) {
            Instant start = Instant.now();
            TestLuaObject testOOP = new TestLuaObject();
            Instant finish = Instant.now();

            long elapsed = Duration.between(start, finish).toMillis();
            logger.info("Elapsed: {}ms", elapsed);
        }

        logger.info("Benchmark Call method");
        for (int i = 0; i < 10; i++) {
            TestLuaObject testOOP = new TestLuaObject();

            Instant start = Instant.now();

            for (int j = 0; j < 10; j++) {
                testOOP.setArea(10);
                int area = testOOP.getArea();
                float floatParam = testOOP.floatParam(0.000001f);
            }

            Instant finish = Instant.now();

            long elapsed = Duration.between(start, finish).toMillis();
            logger.info("Elapsed: {}ms", elapsed);
        }
    }
}