package com.mycompany.controllers;

import com.mycompany.dtos.OrderDTO;
import com.mycompany.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody @Valid OrderDTO orderDTO) {
        LOGGER.warn("Creating a order: {}", orderDTO);
        return ResponseEntity.ok().body(this.orderService.create(orderDTO));
    }

    @PutMapping
    public ResponseEntity<OrderDTO> update(@RequestBody @Valid OrderDTO orderDTO) {
        LOGGER.warn("Updating order {}", orderDTO);
        return ResponseEntity.ok(this.orderService.update(orderDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findBy(@PathVariable("id") String id) {
        LOGGER.warn("find by id: {}", id);
        return ResponseEntity.ok(this.orderService.findById(id));
    }

    @GetMapping("/orderDate")
    public ResponseEntity<List<OrderDTO>> findBy(@RequestParam("from") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate from,
                                                 @RequestParam("to") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate to) {
        LOGGER.warn("find by from:{} - to:{}", from, to);
        return ResponseEntity.ok(this.orderService.findBy(from, to));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        LOGGER.warn("find all orders");
        return ResponseEntity.ok(this.orderService.findAll());
    }
}