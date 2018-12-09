package com.mycompany.mappers;

import com.mycompany.dtos.OrderDTO;
import com.mycompany.dtos.ProductDTO;
import com.mycompany.models.Order;
import com.mycompany.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements Mapper<OrderDTO, Order> {

    @Autowired
    private Mapper<ProductDTO, Product> productMapper;

    @Override
    public OrderDTO makeDTO(Order order) {
        return order == null ? OrderDTO.nullObject() : OrderDTO.builder()
                .id(order.getId())
                .email(order.getEmail())
                .orderDate(order.getOrderDate())
                .products(this.productMapper.makeDtos(order.getProducts()))
                .total(order.getTotal())
                .build();
    }

    @Override
    public Order makeEntity(OrderDTO orderDTO) {
        return orderDTO == null ? Order.nullObject() : Order.builder()
                .id(orderDTO.getId())
                .email(orderDTO.getEmail())
                .orderDate(orderDTO.getOrderDate())
                .products(this.productMapper.makeEntities(orderDTO.getProducts()))
                .build();
    }
}
