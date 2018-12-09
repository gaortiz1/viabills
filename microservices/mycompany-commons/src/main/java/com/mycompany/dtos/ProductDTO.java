package com.mycompany.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductDTO implements Serializable {

    private String id;

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "price must not be null")
    @DecimalMin(value = "0", message = "price must be greater than zero")
    private BigDecimal price;

    public static ProductDTO nullObject() {
        return ProductDTO.builder().build();
    }

}
