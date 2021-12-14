package com.zitga.idle.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.zitga.utils.TimeUtils;

import java.io.IOException;
import java.util.Date;

public class DateDeserializer extends StdDeserializer<Date> {

    public DateDeserializer() {
        this(null);
    }

    public DateDeserializer(Class<Date> clazz) {
        super(clazz);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String dateString = jsonParser.getText();
        long timeInSeconds = Long.parseLong(dateString);

        return TimeUtils.toDateFromSecond(timeInSeconds);
    }
}