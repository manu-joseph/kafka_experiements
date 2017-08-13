package com.mapr.examples.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapr.examples.model.Product;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class GenericObjectDeserializer<T> implements Deserializer<T> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        Objects.requireNonNull(bytes, "The content for deserailzation can not be null");
        ObjectMapper objectMapper = new ObjectMapper();
        T object = null;
        try {
            object = (T) objectMapper.readValue(bytes, Object.class);
            //objectMapper.readValue()
        } catch (IOException e) {
            throw new RuntimeException("Error while deserailization", e);
        }
        return object;
    }

    @Override
    public void close() {

    }
}
