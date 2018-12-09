package com.mycompany.mappers;

import com.mycompany.dtos.ProductDTO;
import com.mycompany.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<ProductDTO, Product> {

    @Override
    public ProductDTO makeDTO(Product product) {
        return product == null ? ProductDTO.nullObject() : ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    @Override
    public Product makeEntity(ProductDTO productDTO) {
        return productDTO == null ? Product.nullObject() : Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }
}
