package com.mycompany.services;

import com.mycompany.dtos.OrderDTO;
import com.mycompany.exceptions.ModelNotFoundedExceptions;
import com.mycompany.mappers.Mapper;
import com.mycompany.models.Order;
import com.mycompany.repositories.OrderRepository;
import com.mycompany.services.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestOrderService {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private Mapper<OrderDTO, Order> orderMapper;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void shouldCreateWhenSendOrderDTO() {

        final OrderDTO orderDTO = OrderDTO.builder()
                .id("1")
                .email("a@a.com")
                .orderDate(LocalDate.now())
                .build();

        final Order order = Order.builder()
                .id("1")
                .email("a@a.com")
                .orderDate(LocalDate.now())
                .build();

        Mockito.when(this.orderMapper.makeEntity(orderDTO)).thenReturn(order);
        Mockito.when(this.orderRepository.save(Mockito.any(Order.class))).thenReturn(Order.builder()
                .id("1")
                .email("a@a.com")
                .orderDate(LocalDate.now())
                .build());

        this.orderService.create(orderDTO);

        Mockito.verify(this.orderMapper, Mockito.times(1)).makeEntity(orderDTO);
        Mockito.verify(this.orderMapper, Mockito.times(1)).makeDTO(Mockito.any(Order.class));
        Mockito.verify(this.orderRepository, Mockito.times(1)).save(Mockito.any(Order.class));
    }

    @Test
    public void shouldNotCreateWhenOrderDTOIsNull() {

        Assertions.assertThrows(NullPointerException.class, ()-> this.orderService.create(null));

        Mockito.verify(this.orderMapper, Mockito.never()).makeEntity(Mockito.any(OrderDTO.class));
        Mockito.verify(this.orderRepository, Mockito.never()).save(Mockito.any(Order.class));
    }


    @Test
    public void shouldUpdateWhenSendOrderDTO() {
        final OrderDTO orderDTO = OrderDTO.builder()
                .id("1")
                .email("b@b.com")
                .orderDate(LocalDate.now())
                .build();

        final Order order = Order.builder()
                .id("1")
                .email("a@a.com")
                .orderDate(LocalDate.now())
                .build();

        Mockito.when(this.orderRepository.findById(orderDTO.getId())).thenReturn(Optional.of(order));
        Mockito.when(this.orderMapper.makeEntity(orderDTO)).thenReturn(order);
        Mockito.when(this.orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        this.orderService.update(orderDTO);

        Mockito.verify(this.orderRepository, Mockito.times(1)).findById(orderDTO.getId());
        Mockito.verify(this.orderMapper, Mockito.times(1)).makeEntity(orderDTO);
        Mockito.verify(this.orderMapper, Mockito.times(1)).makeDTO(Mockito.any(Order.class));
        Mockito.verify(this.orderRepository, Mockito.times(1)).save(Mockito.any(Order.class));
    }

    @Test
    public void shouldNotUpdateWhenOrderNotFound() {

        final OrderDTO orderDTO = OrderDTO.builder()
                .id("1")
                .email("a@a.com")
                .orderDate(LocalDate.now())
                .build();

        final Order order = Order.builder()
                .id("1")
                .email("a@a.com")
                .orderDate(LocalDate.now())
                .build();

        Mockito.when(this.orderRepository.findById(orderDTO.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ModelNotFoundedExceptions.class, ()-> this.orderService.update(orderDTO));

        Mockito.verify(this.orderRepository, Mockito.times(1)).findById(orderDTO.getId());
        Mockito.verify(this.orderRepository, Mockito.never()).save(Mockito.any(Order.class));
    }

    @Test
    public void shouldNotUpdateWhenOrderDTOIsNull() {
        Assertions.assertThrows(NullPointerException.class, ()-> this.orderService.update(null));

        Mockito.verify(this.orderRepository, Mockito.never()).existsById(Mockito.anyString());
        Mockito.verify(this.orderMapper, Mockito.never()).makeEntity(Mockito.any(OrderDTO.class));
        Mockito.verify(this.orderRepository, Mockito.never()).save(Mockito.any(Order.class));
    }

    @Test
    public void shouldFindByAllOrdersWithinGivenTimePeriod() {

        LocalDate from = LocalDate.of(2018, 01, 01);
        LocalDate to = LocalDate.of(2018, 01, 31);

        this.orderService.findBy(from, to);

        Mockito.verify(this.orderRepository, Mockito.times(1)).findBy(from, to);
        Mockito.verify(this.orderMapper, Mockito.times(1)).makeDtos(Mockito.anyListOf(Order.class));
    }

    @Test
    public void shouldValidateTimePeriodWhenFindBy() {

        LocalDate from = LocalDate.of(2018, 02, 01);
        LocalDate to = LocalDate.of(2018, 01, 31);

        Assertions.assertThrows(IllegalArgumentException.class, ()-> this.orderService.findBy(from, to));

        Mockito.verify(this.orderRepository, Mockito.never()).findBy(from, to);
        Mockito.verify(this.orderMapper, Mockito.never()).makeDtos(Mockito.anyListOf(Order.class));
    }

}
