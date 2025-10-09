package com.sales;

import java.util.*;
import java.util.stream.Collectors;

public class OrderMetrics {

    public List<String> getUniqueCities(List<Order> orders){
        return orders.stream().map(order -> order.getCustomer().getCity()).distinct().toList();
    }

    public Double getTotalIncome(List<Order> orders){
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .mapToDouble(order -> order.getItems().stream()
                        .mapToDouble(item -> item.getPrice() * item.getQuantity()).sum())
                            .sum();
    }

    public String getMostPopularProduct(List<Order> orders){
        Map<String, Long> products = orders.stream().flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getProductName, Collectors.counting()));
        return products.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("There aren't any products");
    }

    public OptionalDouble getAverageCheck(List<Order> orders){
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .mapToDouble(order -> order.getItems().stream()
                        .mapToDouble(item -> item.getPrice() * item.getQuantity()).sum())
                .average();
    }

    public List<String> getCustomersWithMoreThan5Orders(List<Order> orders){
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.counting()))
                .entrySet().stream().filter(customer -> customer.getValue() > 5)
                .map(entry -> entry.getKey().getName())
                .toList();
    }

}
