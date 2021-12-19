package com.zitga.idle.pve.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.idle.utils.FileService;
import com.zitga.support.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BeanComponent
public class PveDataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private CsvService csvService;

    @BeanField
    private FileService fileService;

    @BeanMethod
    private void init(){
        logger.info("Initializing PveDataService ...");
    }
}
