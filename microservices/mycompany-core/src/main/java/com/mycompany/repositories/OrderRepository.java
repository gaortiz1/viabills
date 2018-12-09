package com.mycompany.repositories;

import com.mycompany.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{ 'orderDate' : { $gt: ?0, $lt: ?1 } }")
    List<Order> findBy(LocalDate from, LocalDate to);
}
