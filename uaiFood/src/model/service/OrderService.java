package model.service;

import model.entities.Client;
import model.entities.Order;
import model.entities.OrderItem;
import model.entities.enums.OrderStatus;

import java.util.List;

public class OrderService {

    public Order createOrder(Client client, List<OrderItem> items) {
        int numberOrder = (int) (Math.random() * 1000);
        Order order = new Order(numberOrder, client, OrderStatus.PENDING);
        for (OrderItem item : items) {
            order.addItem(item);
        }
        return order;
    }

    public void processPayment(Order order, PaymentService payService) {
        order.payment(payService);
    }
}