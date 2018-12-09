package com.mycompany.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

public class TestProductDTO {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void shouldValidateNameNull() {

        ProductDTO productDTO = ProductDTO.builder()
                .id("1")
                .price(BigDecimal.TEN)
                .build();

        Set<ConstraintViolation<ProductDTO>> violations = this.validator.validate(productDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("name is required", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void shouldValidateNameEmpty() {

        ProductDTO productDTO = ProductDTO.builder()
                .id("1")
                .name(" ")
                .price(BigDecimal.TEN)
                .build();

        Set<ConstraintViolation<ProductDTO>> violations = this.validator.validate(productDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("name is required", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void shouldValidatePriceNull() {

        ProductDTO productDTO = ProductDTO.builder()
                .id("1")
                .name("product 1")
                .build();

        Set<ConstraintViolation<ProductDTO>> violations = this.validator.validate(productDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("price must not be null", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void shouldValidatePriceNegative() {

        ProductDTO productDTO = ProductDTO.builder()
                .id("1")
                .name("product 1")
                .price(BigDecimal.valueOf(-1))
                .build();

        Set<ConstraintViolation<ProductDTO>> violations = this.validator.validate(productDTO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, violations.size()),
                () -> Assertions.assertEquals("price must be greater than zero", violations.iterator().next().getMessage())
        );
    }
}
