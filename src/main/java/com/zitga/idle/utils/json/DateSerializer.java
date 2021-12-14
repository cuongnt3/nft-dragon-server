package com.zitga.idle.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.zitga.utils.TimeUtils;

import java.io.IOException;
import java.util.Date;

public class DateSerializer extends StdSerializer<Date> {

    public DateSerializer() {
        this(null);
    }

    public DateSerializer(Class<Date> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeString(Long.toString(TimeUtils.toSecond(date)));
    }
}