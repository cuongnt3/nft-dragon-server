package com.zitga.idle.lua.object;

import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class TestLuaObject extends LuaObject {

    public TestLuaObject() {
        super("lua/test/lua", "TestOOP");
    }

    public int getArea() {
        return getField("area").toint();
    }

    public void setArea(int area) {
        invoke("SetArea", LuaValue.valueOf(area));
    }

    public int getDummyField() {
        return getField("dummy").toint();
    }

    public void setDummyField(int value) {
        invoke("SetDummy", LuaValue.valueOf(value));
    }

    public int noParam() {
        return invoke("NoParam").toint();
    }

    public int oneParam(int param0) {
        return invoke("OneParam", LuaValue.valueOf(param0)).toint();
    }

    public int twoParam(int param0, int param1) {
        return invoke("TwoParam", LuaValue.valueOf(param0), LuaValue.valueOf(param1)).toint();
    }

    public int threeParam(int param0, int param1, int param2) {
        return invoke("ThreeParam", LuaValue.valueOf(param0), LuaValue.valueOf(param1), LuaValue.valueOf(param2)).toint();
    }

    public float floatParam(float param0) {
        return invoke("FloatParam", LuaValue.valueOf(param0)).tofloat();
    }
}