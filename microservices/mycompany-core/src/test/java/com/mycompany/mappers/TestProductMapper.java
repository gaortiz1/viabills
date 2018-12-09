package com.mycompany.mappers;

import com.mycompany.dtos.ProductDTO;
import com.mycompany.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestProductMapper {

    private ProductMapper productMapper;

    @BeforeEach
    public void setUp() {
        this.productMapper = new ProductMapper();
    }

    @Test
    public void shouldMakeDtoWhenSendEntityNotEmpty() {
        ProductDTO productDTO = this.productMapper.makeDTO(Product.builder()
                .name("product 1")
                .price(BigDecimal.TEN)
                .build());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(productDTO),
                () -> Assertions.assertEquals("product 1", productDTO.getName()),
                () -> Assertions.assertEquals(BigDecimal.TEN, productDTO.getPrice())
        );
    }

    @Test
    public void shouldMakeDtoEmptyWhenSendNull() {
        ProductDTO productDTO = this.productMapper.makeDTO(null);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(productDTO),
                () -> Assertions.assertNull(productDTO.getName()),
                () -> Assertions.assertNull(productDTO.getPrice())
        );
    }

    @Test
    public void shouldMakeEntityWhenSendDTONotEmpty() {
        Product product = this.productMapper.makeEntity(ProductDTO.builder()
                .name("item")
                .price(BigDecimal.TEN)
                .build());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(product),
                () -> Assertions.assertEquals("item", product.getName()),
                () -> Assertions.assertEquals(BigDecimal.TEN, product.getPrice())
        );
    }

    @Test
    public void shouldMakeEntityEmptyWhenSendDTONull() {
        Product item = this.productMapper.makeEntity(null);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(item),
                () -> Assertions.assertNull(item.getName()),
                () -> Assertions.assertNull(item.getPrice())
        );
    }

}
