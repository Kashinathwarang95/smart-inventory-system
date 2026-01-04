package com.smartinventory.inventoryservice.service;

import com.smartinventory.inventoryservice.dto.OrderRequestDTO;
import com.smartinventory.inventoryservice.entity.Order;
import com.smartinventory.inventoryservice.entity.OrderItem;
import com.smartinventory.inventoryservice.entity.Product;
import com.smartinventory.inventoryservice.repository.OrderRepository;
import com.smartinventory.inventoryservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService (ProductRepository productRepository,OrderRepository orderRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

//    place order
@Transactional
    public Order placeOrder(OrderRequestDTO dto){
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(()->new RuntimeException("Product not found"));
        if (product.getStock()<dto.getQuantity()){
            throw new RuntimeException("Not enough stock");
        }
//    reduce stock
        product.setStock(product.getStock()-dto.getQuantity());
        productRepository.save(product);

        // create order

        Order order = new Order();
        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        item.setPrice(product.getPrice());
        item.setOrder(order);

        List<OrderItem> items = new ArrayList<>();
        items.add(item);
        order.setOrderItems(items);
        order.setTotalAmount(product.getPrice()*dto.getQuantity());

        return orderRepository.save(order);
    }

    @Transactional
    public Order cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException("Order already cancelled");
        }

        // Restore stock
        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            product.setStock(product.getStock() + item.getQuantity());
        }

        order.setStatus(OrderStatus.CANCELLED);

        return orderRepository.save(order);
    }
}

