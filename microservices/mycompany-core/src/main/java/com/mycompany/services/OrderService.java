package com.mycompany.services;

import com.mycompany.dtos.OrderDTO;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO update(OrderDTO orderDTO);

    OrderDTO findById(String id);

    List<OrderDTO> findAll();

    List<OrderDTO> findBy(LocalDate from, LocalDate to);

}
