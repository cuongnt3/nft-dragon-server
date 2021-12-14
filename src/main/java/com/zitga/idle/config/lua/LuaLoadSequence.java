package com.zitga.idle.config.lua;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LuaLoadSequence {

    @JsonProperty("is_folder")
    private boolean isFolder;

    @JsonProperty("paths")
    private List<String> paths;

    public boolean isFolder() {
        return isFolder;
    }

    public List<String> getPaths() {
        return paths;
    }
}
