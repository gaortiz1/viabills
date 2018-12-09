package com.mycompany.services;

import com.mycompany.dtos.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(ProductDTO productDTO);

    ProductDTO findById(String id);

    List<ProductDTO> findAll();

}
