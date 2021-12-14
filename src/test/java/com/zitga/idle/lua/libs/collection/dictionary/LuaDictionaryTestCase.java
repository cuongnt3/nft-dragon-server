package com.zitga.idle.lua.libs.collection.dictionary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LuaDictionaryTestCase {

    @Test
    public void test() {
        TestLuaDictionary testLuaDictionary = new TestLuaDictionary();
        Assertions.assertNotNull(testLuaDictionary);

        testLuaDictionary.test();
    }
}
