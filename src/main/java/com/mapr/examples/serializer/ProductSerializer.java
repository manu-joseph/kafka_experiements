package com.mapr.examples.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapr.examples.model.Product;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.Objects;

public class ProductSerializer implements Serializer<Product>{

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Product product) {
        Objects.requireNonNull(product, "The Product can not be null");
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] content = null;
        try {
            content = objectMapper.writeValueAsBytes(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error during serailization", e);
        }
        return content;
    }

    @Override
    public void close() {

    }
}
