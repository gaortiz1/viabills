package com.mycompany.mappers;

import com.mycompany.dtos.OrderDTO;
import com.mycompany.dtos.ProductDTO;
import com.mycompany.models.Order;
import com.mycompany.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestOrderMapper {

    @InjectMocks
    private OrderMapper orderMapper;

    @Mock
    private Mapper<ProductDTO, Product> productMapper;

    @Test
    public void shouldMakeDtoWhenSendEntityNotEmpty() {

        LocalDate now = LocalDate.now();
        OrderDTO orderDTO = this.orderMapper.makeDTO(Order.builder()
                .id("1")
                .email("a@a.com")
                .orderDate(now)
                .build());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(orderDTO),
                () -> Assertions.assertEquals("1", orderDTO.getId()),
                () -> Assertions.assertEquals("a@a.com", orderDTO.getEmail()),
                () -> Assertions.assertEquals(now, orderDTO.getOrderDate())
        );
    }

    @Test
    public void shouldMakeDtoEmptyWhenSendNull() {
        OrderDTO orderDTO = this.orderMapper.makeDTO(null);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(orderDTO),
                () -> Assertions.assertNull(orderDTO.getId()),
                () -> Assertions.assertNull(orderDTO.getEmail()),
                () -> Assertions.assertNull(orderDTO.getOrderDate())
        );
    }

    @Test
    public void shouldMakeEntityWhenSendDTONotEmpty() {
        LocalDate now = LocalDate.now();
        Order order = this.orderMapper.makeEntity(OrderDTO.builder()
                .id("1")
                .email("a@a.com")
                .orderDate(now)
                .build());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(order),
                () -> Assertions.assertEquals("1", order.getId()),
                () -> Assertions.assertEquals("a@a.com", order.getEmail()),
                () -> Assertions.assertEquals(now, order.getOrderDate())
        );
    }

    @Test
    public void shouldMakeEntityEmptyWhenSendDTONull() {
        final Order order = this.orderMapper.makeEntity(null);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(order),
                () -> Assertions.assertNull(order.getId()),
                () -> Assertions.assertNull(order.getEmail()),
                () -> Assertions.assertNull(order.getOrderDate())
        );
    }

}
