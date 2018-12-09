package com.mycompany.controllers;

import com.mycompany.dtos.ProductDTO;
import com.mycompany.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO productDTO) {
        LOGGER.warn("Creating a product: {}", productDTO);
        return ResponseEntity.ok().body(this.productService.create(productDTO));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody @Valid ProductDTO productDTO) {
        LOGGER.warn("Updating product {}", productDTO);
        return ResponseEntity.ok(this.productService.update(productDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findBy(@PathVariable("id") String id) {
        LOGGER.warn("find by id: {}", id);
        return ResponseEntity.ok(this.productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        LOGGER.warn("find all products");
        return ResponseEntity.ok(this.productService.findAll());
    }

}
