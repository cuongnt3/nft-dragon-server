package com.zitga.idle.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.bson.types.ObjectId;

import java.io.IOException;

public class ObjectIdSerializer extends StdSerializer<ObjectId> {

    public ObjectIdSerializer() {
        this(null);
    }

    public ObjectIdSerializer(Class<ObjectId> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(ObjectId id, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeString(id.toString());
    }
}