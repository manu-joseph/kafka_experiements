package com.mapr.examples.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapr.examples.model.Product;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ProductDeserializer implements Deserializer<Product> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Product deserialize(String s, byte[] bytes) {
        Objects.requireNonNull(bytes, "The content for deserailzation can not be null");
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = null;
        try {
            product = objectMapper.readValue(bytes, Product.class);
        } catch (IOException e) {
           throw new RuntimeException("Error while deserailization", e);
        }
        return product;
    }

    @Override
    public void close() {

    }
}
