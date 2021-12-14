package com.zitga.idle.localization.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanDelayedMethod;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.idle.localization.constant.LocalizationConstant;
import com.zitga.idle.localization.constant.LocalizationKey;
import com.zitga.idle.localization.constant.LocalizationTag;
import com.zitga.idle.localization.utils.LocalizationCsvPathUtils;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.utils.FileService;
import com.zitga.support.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class LocalizationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private FileService fileService;

    @BeanField
    private CsvService csvService;

    // key: localization key
    private Map<String, String> localizationMap;

    // key: localization key
    private List<String> languageList;

    @BeanMethod
    private void init() {
        logger.info("Initializing LocalizationService ...");

        createLocalizationConfig();
    }

    @BeanDelayedMethod
    private void validate() throws Exception {
        Object obj = new Object();
        Field[] fields = LocalizationKey.class.getDeclaredFields();
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            for (String language : languageList) {
                String key = field.get(obj).toString();

                if (!localizationMap.containsKey(toLocalizedTag(key, language))) {
                    throw new RuntimeException(String.format("Localization key is not found, key = %s, language = %s",
                            key, language));
                }
            }
        }
    }

    public void createLocalizationConfig() {
        List<File> listCsvFile = fileService.listCsvFile(LocalizationCsvPathUtils.getLocalizationMailPath());

        localizationMap = new ConcurrentHashMap<>();
        languageList = new ArrayList<>();

        for (File file : listCsvFile) {
            String fileName = file.getName(); //example fileName: en.csv

            //now treat fileName as a language!
            String language = fileName.split("\\.")[0];  //example fileName: en
            String csvString = fileService.readFileContent(file);

            languageList.add(language);

            List<Map<String, String>> csvData = csvService.read(csvString, LocalizationConstant.SEPARATOR_TAG);

            for (Map<String, String> data : csvData) {
                String key = data.get(LocalizationTag.KEY_TAG);
                String content = data.get(LocalizationTag.VALUE_TAG);

                if (key == null || content == null) {
                    throw new RuntimeException("Key or content is null");
                }

                localizationMap.put(toLocalizedTag(key, language), content);
            }
        }
    }

    public String getLocalizationContent(String language, String localizedKey) {
        String tag = toLocalizedTag(localizedKey, language);
        return localizationMap.get(tag);
    }

    public List<String> getLanguageList() {
        return languageList;
    }

    public boolean isValidLanguage(String language) {
        return languageList.contains(language);
    }

    private String toLocalizedTag(String localizedKey, String language) {
        return String.format("%s_%s", language, localizedKey);
    }
}
