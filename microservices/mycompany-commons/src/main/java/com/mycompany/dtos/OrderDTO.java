package com.mycompany.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderDTO implements Serializable {

    private String id;

    @NotEmpty(message = "email address is required")
    @Email(message = "email address must be valid")
    private String email;

    @NotNull(message = "orderDate is required")
    @FutureOrPresent(message = "orderDate must not be a past date")
    private LocalDate orderDate;

    private BigDecimal total;

    @NotEmpty(message = "products must have at least one item")
    private List<ProductDTO> products;


    public static OrderDTO nullObject() {
        return OrderDTO.builder().build();
    }
}
