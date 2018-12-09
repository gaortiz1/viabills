package com.mycompany.services;

import com.mycompany.dtos.ProductDTO;
import com.mycompany.exceptions.ModelNotFoundedExceptions;
import com.mycompany.mappers.Mapper;
import com.mycompany.models.Product;
import com.mycompany.repositories.ProductRepository;
import com.mycompany.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestProductService {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private Mapper<ProductDTO, Product> productMapper;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void shouldCreateWhenSendProductDTO() {

        final ProductDTO productDTO = ProductDTO.builder()
                .name("product 1")
                .price(BigDecimal.TEN)
                .build();

        Product product = Product.builder()
                .id("1")
                .name("product 1")
                .price(BigDecimal.TEN)
                .build();

        Mockito.when(this.productMapper.makeEntity(productDTO)).thenReturn(product);
        Mockito.when(this.productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        this.productService.create(productDTO);

        Mockito.verify(this.productMapper, Mockito.times(1)).makeEntity(productDTO);
        Mockito.verify(this.productMapper, Mockito.times(1)).makeDTO(Mockito.any(Product.class));
        Mockito.verify(this.productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
    }

    @Test
    public void shouldNotCreateWhenProductDTOIsNull() {

        Assertions.assertThrows(NullPointerException.class, ()-> this.productService.create(null));

        Mockito.verify(this.productMapper, Mockito.never()).makeEntity(Mockito.any(ProductDTO.class));
        Mockito.verify(this.productRepository, Mockito.never()).save(Mockito.any(Product.class));
    }


    @Test
    public void shouldUpdateWhenSendProductDTO() {

        final ProductDTO productDTO = ProductDTO.builder()
                .id("1")
                .name("product 2")
                .price(BigDecimal.TEN)
                .build();

        Product product = Product.builder()
                .id("1")
                .name("product 1")
                .price(BigDecimal.TEN)
                .build();

        Mockito.when(this.productRepository.existsById(productDTO.getId())).thenReturn(Boolean.TRUE);
        Mockito.when(this.productMapper.makeEntity(productDTO)).thenReturn(product);
        Mockito.when(this.productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        this.productService.update(productDTO);

        Mockito.verify(this.productRepository, Mockito.times(1)).existsById(productDTO.getId());
        Mockito.verify(this.productMapper, Mockito.times(1)).makeEntity(productDTO);
        Mockito.verify(this.productMapper, Mockito.times(1)).makeDTO(Mockito.any(Product.class));
        Mockito.verify(this.productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
    }

    @Test
    public void shouldNotUpdateWhenProductNotFound() {

        final ProductDTO productDTO = ProductDTO.builder()
                .id("1")
                .name("product 1")
                .price(BigDecimal.TEN)
                .build();

        Mockito.when(this.productRepository.existsById(productDTO.getId())).thenReturn(Boolean.FALSE);

        Assertions.assertThrows(ModelNotFoundedExceptions.class, ()-> this.productService.update(productDTO));

        Mockito.verify(this.productRepository, Mockito.times(1)).existsById(productDTO.getId());
    }

    @Test
    public void shouldNotUpdateWhenProductDTOIsNull() {

        Assertions.assertThrows(NullPointerException.class, ()-> this.productService.update(null));

        Mockito.verify(this.productRepository, Mockito.never()).existsById(Mockito.anyString());
        Mockito.verify(this.productMapper, Mockito.never()).makeEntity(Mockito.any(ProductDTO.class));
        Mockito.verify(this.productRepository, Mockito.never()).save(Mockito.any(Product.class));
    }
}
