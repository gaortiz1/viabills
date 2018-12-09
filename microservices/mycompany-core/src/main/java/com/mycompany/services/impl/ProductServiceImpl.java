package com.mycompany.services.impl;

import com.google.common.base.Preconditions;
import com.mycompany.dtos.ProductDTO;
import com.mycompany.exceptions.ModelNotFoundedExceptions;
import com.mycompany.mappers.Mapper;
import com.mycompany.models.Product;
import com.mycompany.repositories.ProductRepository;
import com.mycompany.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Mapper<ProductDTO, Product> productMapper;

    @Override
    public ProductDTO create(ProductDTO productDTO) {

        Preconditions.checkNotNull(productDTO, "product can not be null");

        final Product newProduct = this.productMapper.makeEntity(productDTO);
        return this.productMapper.makeDTO(this.productRepository.save(newProduct));
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {

        Preconditions.checkNotNull(productDTO, "product can not be null");
        Preconditions.checkNotNull(productDTO.getId(), "productId can not be null");

        if (!this.productRepository.existsById(productDTO.getId())) {
            throw new ModelNotFoundedExceptions("Order not found");
        }

        return this.create(productDTO);
    }

    @Override
    public ProductDTO findById(String id) {
        return this.productMapper.makeDTO(this.productRepository.findById(id).orElse(Product.nullObject()));
    }

    @Override
    public List<ProductDTO> findAll() {
        return this.productMapper.makeDtos(this.productRepository.findAll());
    }
}
