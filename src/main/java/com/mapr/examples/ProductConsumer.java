package com.mapr.examples;

import com.google.common.io.Resources;
import com.mapr.examples.model.Product;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

public class ProductConsumer {

    public static void main(String[] args) throws IOException {
        KafkaConsumer<String, Product> consumer;
        try ( InputStream props = Resources.getResource ( "consumer.props" ).openStream ( ) ) {
            Properties properties = new Properties ( );
            properties.load ( props );
            if ( properties.getProperty ( "group.id" ) == null ) {
                properties.setProperty ( "group.id", "group-" + new Random ( ).nextInt ( 100000 ) );
            }
            consumer = new KafkaConsumer <> ( properties );
        }
        consumer.subscribe(Arrays.asList("products"));
        while (true) {
            ConsumerRecords<String, Product> records = consumer.poll (100);
            records.forEach ((r) -> {
                processRecord (r);
            });

        }


    }

    private static void processRecord(final ConsumerRecord<String, Product> porductRecord) {
        String topic = porductRecord.topic();
        switch(topic) {
            case "products":
                Product product = porductRecord.value();
                System.out.println(product);
                break;
            default:
                System.out.println("Unknown Topic");
        }
    }
}
