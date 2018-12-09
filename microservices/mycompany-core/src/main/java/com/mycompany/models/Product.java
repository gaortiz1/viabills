package com.mycompany.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@Document(collection = "products")
public class Product implements Serializable {

    @Id
    private String id;
    private String name;
    private BigDecimal price;

    public static Product nullObject() {
        return Product.builder().build();
    }
}
