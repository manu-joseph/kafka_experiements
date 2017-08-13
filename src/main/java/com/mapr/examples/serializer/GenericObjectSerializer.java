package com.mapr.examples.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.Objects;

public class GenericObjectSerializer<T> implements Serializer<T> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, T t) {
        Objects.requireNonNull(t, "The object can not be null");
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] content = null;
        try {
            content = objectMapper.writeValueAsBytes(t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error during serailization", e);
        }
        return content;
    }

    @Override
    public void close() {

    }
}
