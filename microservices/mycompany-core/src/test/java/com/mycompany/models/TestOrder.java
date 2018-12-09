package com.mycompany.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

public class TestOrder {

    @Test
    public void shouldHaveCreationDateAndEmailAndProducts() {

        final LocalDate now = LocalDate.now();

        final Order order = Order.builder()
                .email("a@a.com")
                .orderDate(now)
                .products(Collections.singletonList(Product.builder()
                        .id("1")
                        .name("product 1")
                        .price(BigDecimal.TEN)
                        .build()))
                .build();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(order),
                () -> Assertions.assertEquals("a@a.com", order.getEmail()),
                () -> Assertions.assertEquals(now, order.getOrderDate()),
                () -> Assertions.assertEquals(1, order.getProducts().size())
        );
    }

    @Test
    public void shouldHaveTotal() {

        final Order order = Order.builder()
                .products(Collections.singletonList(Product.builder()
                        .id("1")
                        .name("product 1")
                        .price(BigDecimal.TEN)
                        .build()))
                .build();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(order),
                () -> Assertions.assertEquals(BigDecimal.TEN, order.getTotal())
        );
    }
}
