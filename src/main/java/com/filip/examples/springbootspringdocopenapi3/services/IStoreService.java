package com.filip.examples.springbootspringdocopenapi3.services;

import com.filip.examples.springbootspringdocopenapi3.models.Order;

import java.util.List;
import java.util.Map;

public interface IStoreService {

    void deleteOrder(String orderId);

    Map<String, Integer> getInventory();

    Order getOrderById(Long orderId);

    Order placeOrder(Order order);

    List<Order> getall();
}
