package com.zitga.idle.config.lua;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.bean.annotation.BeanConfiguration;
import com.zitga.support.config.ConfigJsonReader;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@BeanConfiguration(value = "config/lua/lua_load_rule.json", reader = ConfigJsonReader.class)
public class LuaLoadRuleConfig {

    @JsonProperty("load_sequences")
    private List<LuaLoadSequence> loadSequences;

    @JsonProperty("exclude_folders")
    private List<String> excludeFolders;
    @JsonProperty("exclude_files")
    private List<String> excludeFiles;

    @JsonProperty("generate_path")
    private Map<String, String> generatePath;

    @JsonProperty("dynamic_load")
    private LuaDynamicLoadRule dynamicLoadRule;

    public List<LuaLoadSequence> getLoadSequences() {
        return loadSequences;
    }

    public LuaDynamicLoadRule getDynamicLoadRule() {
        return dynamicLoadRule;
    }

    public boolean isFolderExcluded(String path) {
        path = path.replace("\\", "/");
        for (String excludeFolder : excludeFolders) {
            Pattern pattern = Pattern.compile(excludeFolder);
            Matcher matcher = pattern.matcher(path);

            if (matcher.find()) {
                return true;
            }
        }

        return false;
    }

    public boolean isFileExcluded(String path) {
        // Remove extension
        int index = path.indexOf("\\lua\\");
        path = path.substring(index + 1, path.length() - 4);

        return excludeFiles.contains(path);
    }

    public String getLuaNameToGenerate() {
        return generatePath.get("lua_file");
    }
}
