package com.mycompany.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

public class TestOrderDTO {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void shouldValidateEmailWrong() {

        OrderDTO orderDTO = OrderDTO.builder()
                .id("1")
                .orderDate(LocalDate.now())
                .email("it's not email")
                .products(Collections.singletonList(ProductDTO.builder()
                        .name("product 1")
                        .price(BigDecimal.TEN)
                        .build()))
                .build();

        Set<ConstraintViolation<OrderDTO>> violations = this.validator.validate(orderDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("email address must be valid", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void shouldValidateOrderDateWrong() {

        LocalDate past = LocalDate.of(2016, 01, 01);

        OrderDTO orderDTO = OrderDTO.builder()
                .id("1")
                .orderDate(past)
                .email("a@a.com")
                .products(Collections.singletonList(ProductDTO.builder()
                        .name("product 1")
                        .price(BigDecimal.TEN)
                        .build()))
                .build();

        Set<ConstraintViolation<OrderDTO>> violations = this.validator.validate(orderDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("orderDate must not be a past date", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void shouldValidateHaveProducts() {

        OrderDTO orderDTO = OrderDTO.builder()
                .id("1")
                .orderDate(LocalDate.now())
                .email("a@a.com")
                .build();

        Set<ConstraintViolation<OrderDTO>> violations = this.validator.validate(orderDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("products must have at least one item", violations.iterator().next().getMessage())
        );
    }
}
