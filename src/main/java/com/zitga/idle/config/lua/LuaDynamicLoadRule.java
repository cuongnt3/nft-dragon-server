package com.zitga.idle.config.lua;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LuaDynamicLoadRule {

    @JsonProperty("paths")
    private List<String> paths;

    @JsonProperty("excludes")
    private List<String> excludes;

    public List<String> getPaths() {
        return paths;
    }

    public boolean isExclude(String path) {
        for (String exclude : excludes) {
            if (path.contains(exclude)) {
                return true;
            }
        }

        return false;
    }
}
