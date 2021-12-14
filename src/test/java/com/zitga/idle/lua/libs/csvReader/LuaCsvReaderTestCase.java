package com.zitga.idle.lua.libs.csvReader;

import com.zitga.utils.ResourcesUtils;
import com.zitga.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LuaCsvReaderTestCase {
    @Test
    public void test() {
        String testCsv = ResourcesUtils.getText("csv/test/libs/test_csv_reader.csv");
        Assertions.assertFalse(StringUtils.isNullOrEmpty(testCsv));

        TestLuaCsvReader testLuaCsvReader = new TestLuaCsvReader();
        testLuaCsvReader.test(testCsv);
    }
}
