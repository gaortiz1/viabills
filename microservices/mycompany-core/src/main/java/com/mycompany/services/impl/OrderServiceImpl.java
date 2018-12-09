package com.mycompany.services.impl;

import com.google.common.base.Preconditions;
import com.mycompany.dtos.OrderDTO;
import com.mycompany.exceptions.ModelNotFoundedExceptions;
import com.mycompany.mappers.Mapper;
import com.mycompany.models.Order;
import com.mycompany.repositories.OrderRepository;
import com.mycompany.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Mapper<OrderDTO, Order> orderMapper;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        Preconditions.checkNotNull(orderDTO, "order can not be null");

        final Order newOrder = this.orderMapper.makeEntity(orderDTO);
        newOrder.setOrderDate(LocalDate.now());
        return this.orderMapper.makeDTO(this.orderRepository.save(newOrder));
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {

        Preconditions.checkNotNull(orderDTO, "order can not be null");
        Preconditions.checkNotNull(orderDTO.getId(), "orderId can not be null");

        Optional<Order> optionalOrder = this.orderRepository.findById(orderDTO.getId());

        if (!optionalOrder.isPresent()) {
            throw new ModelNotFoundedExceptions("Order not found");
        }

        final Order order = this.orderMapper.makeEntity(orderDTO);
        order.setOrderDate(optionalOrder.get().getOrderDate());
        return this.orderMapper.makeDTO(this.orderRepository.save(order));
    }

    @Override
    public OrderDTO findById(String id) {
        return this.orderMapper.makeDTO(this.orderRepository.findById(id).orElse(Order.nullObject()));
    }

    @Override
    public List<OrderDTO> findAll() {
        return this.orderMapper.makeDtos(this.orderRepository.findAll());
    }

    @Override
    public List<OrderDTO> findBy(LocalDate from, LocalDate to) {
        Preconditions.checkNotNull(from, "parameter from can not be null");
        Preconditions.checkNotNull(to, "to from can not be null");
        Preconditions.checkArgument(from.isBefore(to), "'from' can not be greater than 'to'");

        return this.orderMapper.makeDtos(this.orderRepository.findBy(from, to));
    }
}
