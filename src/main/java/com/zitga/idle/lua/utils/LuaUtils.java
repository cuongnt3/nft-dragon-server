package com.zitga.idle.lua.utils;

import com.zitga.idle.lua.constants.LuaConstants;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.luaj.vm2.luajc.LuaJC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class LuaUtils {

    private static final Logger logger = LoggerFactory.getLogger(LuaUtils.class);

    private static final Globals globals;
    private static final LuaValue doFile;

    private static final Set<String> loadedModules;

    static {
        globals = JsePlatform.debugGlobals();
        LuaJC.install(globals);
        doFile = globals.get("dofile");

        loadedModules = new HashSet<>();
    }

    public static void loadModule(String path) {
        if (!loadedModules.contains(path)) {
            try {
                doFile.call(LuaValue.valueOf(path));
                loadedModules.add(path);
            } catch (Exception e) {
                logger.error("path = {}", path);
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

    public static LuaValue getModule(String moduleName) {
        try {
            return globals.get(moduleName);
        } catch (Exception e) {
            logger.error("moduleName = {}", moduleName);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static LuaValue createInstance(String path, String moduleName) {
        try {
            loadModule(path);
            LuaValue module = globals.get(moduleName);

            LuaValue constructor = module.getmetatable().get(LuaConstants.CONSTRUCTOR_METHOD_NAME);
            return constructor.call(module);
        } catch (Exception e) {
            logger.error("path = {}, moduleName = {}", path, moduleName);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static LuaValue createInstance(String path, String moduleName, LuaValue param0) {
        try {
            loadModule(path);
            LuaValue module = globals.get(moduleName);

            LuaValue constructor = module.getmetatable().get(LuaConstants.CONSTRUCTOR_METHOD_NAME);
            return constructor.call(module, param0);
        } catch (Exception e) {
            logger.error("path = {}, moduleName = {}", path, moduleName);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static LuaValue createInstance(String path, String moduleName, LuaValue param0, LuaValue param1) {
        try {
            loadModule(path);
            LuaValue module = globals.get(moduleName);

            LuaValue constructor = module.getmetatable().get(LuaConstants.CONSTRUCTOR_METHOD_NAME);
            return constructor.call(module, param0, param1);
        } catch (Exception e) {
            logger.error("path = {}, moduleName = {}", path, moduleName);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    // method call helpers
    public static LuaValue invoke(LuaValue luaBinding, String method) {
        try {
            return luaBinding.get(method).call();
        } catch (Exception e) {
            logger.error("luaBinding = {}, method = {}", luaBinding, method);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static LuaValue invoke(LuaValue luaBinding, String method, LuaValue param0) {
        try {
            return luaBinding.get(method).call(param0);
        } catch (Exception e) {
            logger.error("luaBinding = {}, method = {}", luaBinding, method);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static LuaValue invoke(LuaValue luaBinding, String method, LuaValue param0, LuaValue param1) {
        try {
            return luaBinding.get(method).call(param0, param1);
        } catch (Exception e) {
            logger.error("luaBinding = {}, method = {}", luaBinding, method);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static LuaValue invoke(LuaValue luaBinding, String method, LuaValue... params) {
        try {
            return luaBinding.get(method).invoke(params).arg1();
        } catch (Exception e) {
            logger.error("luaBinding = {}, method = {}", luaBinding, method);
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}