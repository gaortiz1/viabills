package com.mycompany.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestProduct {

    @Test
    public void shouldHaveANameAndPrice() {
        final Product product = Product.builder()
                .name("product")
                .price(BigDecimal.TEN)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(product),
                () -> Assertions.assertEquals("product", product.getName()),
                () -> Assertions.assertEquals(BigDecimal.TEN, product.getPrice())
        );
    }
}
