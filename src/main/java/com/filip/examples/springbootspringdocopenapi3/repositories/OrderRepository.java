package com.filip.examples.springbootspringdocopenapi3.repositories;

import com.filip.examples.springbootspringdocopenapi3.models.Order;
import com.filip.examples.springbootspringdocopenapi3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    Optional<Order> findById(Long id);
}
