package com.mycompany.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Data
@Builder
@Document(collection = "orders")
public class Order implements Serializable {

    @Id
    private String id;
    private String email;
    private LocalDate orderDate;
    private List<Product> products;

    public List<Product> getProducts() {
        return this.products == null ?
                Collections.emptyList():
                Collections.unmodifiableList(this.products);
    }

    public BigDecimal getTotal() {
        return this.products == null ?
                BigDecimal.ZERO:
                this.products.stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.TEN);
    }

    public static Order nullObject() {
        return Order.builder().build();
    }
}
