package com.filip.examples.springbootspringdocopenapi3.services;

import com.filip.examples.springbootspringdocopenapi3.models.Order;
import com.filip.examples.springbootspringdocopenapi3.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void deleteOrder(String orderId) {
        orderRepository.delete(getOrderById(Long.valueOf(orderId)));
    }

    @Override
    public Map<String, Integer> getInventory() {

        //ApiUtil.checkApiKey(request);

        return null;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Order placeOrder(Order order) {
        return orderRepository.save(order);

    }

    @Override
    public List<Order> getall() {
        return orderRepository.findAll();
    }
}
