package com.mapr.examples.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable{

    private final String sku;

    private final String description;

    @JsonCreator
    public Product(@JsonProperty("sku") String sku, @JsonProperty("description")String description) {
        this.sku = sku;
        this.description = description;
    }

    @JsonProperty
    public String getSku() {
        return sku;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "sku='" + sku + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

