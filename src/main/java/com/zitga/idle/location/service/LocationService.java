package com.zitga.idle.location.service;

import com.maxmind.db.CHMCache;
import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.idle.location.constant.LocationConstant;
import com.zitga.utils.ResourcesUtils;
import com.zitga.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.InetAddress;

@BeanComponent
public class LocationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private DatabaseReader reader;

    @BeanMethod
    private void init() throws Exception {
        InputStream inputStream = ResourcesUtils.getInputStream(LocationConstant.LOCATION_DB_PATH);
        reader = new DatabaseReader.Builder(inputStream)
                .fileMode(Reader.FileMode.MEMORY)
                .withCache(new CHMCache())
                .build();
    }

    public String getCountry(String address) {
        String result = getCountryForTracking(address);
        if (result == null) {
            result = "";
        }

        return result;
    }

    public String getCountryForTracking(String address) {
        String result = "";

        if (!StringUtils.isNullOrEmpty(address) && !address.trim().equals(LocationConstant.LOCAL_HOST)) {
            try {
                InetAddress ipAddress = InetAddress.getByName(address);
                CityResponse response = reader.city(ipAddress);
                if (response != null) {
                    result = response.getCountry().getIsoCode().toLowerCase();
                }
            } catch (Exception e) {
                logger.debug(e.getMessage(), e);
            }
        }

        return result;
    }
}
