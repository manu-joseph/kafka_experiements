package com.mapr.examples;

import com.google.common.io.Resources;
import com.mapr.examples.model.Product;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductProducer {



    public static void main(String[] args) throws Exception{
        KafkaProducer<String, Product> producer;
        try (InputStream props = Resources.getResource("producer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<>(properties);
        }
        try {
            final List<Product> products = getProducts("Test");
            products.stream().forEach((p) -> {producer.send(new ProducerRecord<String, Product>("products", p));  producer.flush();});
        }finally {
            producer.close();
        }

    }

    private  static  void sendProduct(Product product) {

    }

    private static List<Product> getProducts(final String description) {
       return IntStream.rangeClosed(0, 1000).mapToObj((i) -> new Product("sku"+i, description)).
                collect(Collectors.toList());
    }
}
