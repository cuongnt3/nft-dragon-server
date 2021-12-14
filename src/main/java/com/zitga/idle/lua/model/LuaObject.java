package com.zitga.idle.lua.model;

import com.zitga.idle.lua.utils.LuaUtils;
import org.luaj.vm2.LuaValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuaObject {

    private static final Logger logger = LoggerFactory.getLogger(LuaObject.class);

    private LuaValue luaBinding;

    public LuaObject(String path, String className) {
        String luaPath = String.format("%s/%s.lua", path, className);
        this.luaBinding = LuaUtils.createInstance(luaPath, className);
    }

    public LuaObject(String path, String className, LuaValue param0) {
        String luaPath = String.format("%s/%s.lua", path, className);
        this.luaBinding = LuaUtils.createInstance(luaPath, className, param0);
    }

    public LuaObject(String path, String className, LuaValue param0, LuaValue param1) {
        String luaPath = String.format("%s/%s.lua", path, className);
        this.luaBinding = LuaUtils.createInstance(luaPath, className, param0, param1);
    }

    public LuaObject(LuaValue luaBinding) {
        if (luaBinding == null) {
            throw new RuntimeException("Lua binding is null");
        }

        this.luaBinding = luaBinding;
    }

    public LuaValue toLua() {
        return luaBinding;
    }

    public LuaValue getField(String fieldName) {
        try {
            return luaBinding.get(fieldName);
        } catch (Exception e) {
            logger.error("fieldName = {}", fieldName);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public LuaValue invoke(String methodName) {
        try {
            return luaBinding.get(methodName).call(luaBinding);
        } catch (Exception e) {
            logger.error("methodName = {}", methodName);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public LuaValue invoke(String methodName, LuaValue param0) {
        try {
            return luaBinding.get(methodName).call(luaBinding, param0);
        } catch (Exception e) {
            logger.error("methodName = {}", methodName);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public LuaValue invoke(String methodName, LuaValue param0, LuaValue param1) {
        try {
            return luaBinding.get(methodName).call(luaBinding, param0, param1);
        } catch (Exception e) {
            logger.error("methodName = {}", methodName);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public LuaValue invoke(String methodName, LuaValue... params) {
        try {
            LuaValue[] newParams = new LuaValue[params.length + 1];

            newParams[0] = luaBinding;
            System.arraycopy(params, 0, newParams, 1, params.length);

            return luaBinding.get(methodName).invoke(newParams).arg1();
        } catch (Exception e) {
            logger.error("methodName = {}", methodName);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}