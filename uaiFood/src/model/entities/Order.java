package model.entities;

import model.exceptions.DomainException;

import java.util.List;

public class Order {

    private int number;

    private Client client;
    private List<OrderItem> itemList;

    public Order() {
    }

    public Order(Integer number, Client client) {
        setNumber(number);
        this.client = client;
    }

    public int getNumber() {
        return number;
    }

    public Client getClient() {
        return client;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setNumber(int number) {
        if (number <= 0) {
            throw new DomainException("Error: Number cannot be less or equal than zero!");
        }
        this.number = number;
    }

    public void addItem(OrderItem orderItem) {
        itemList.add(orderItem);
    }

    public void removeItem(String nameItem) {
        OrderItem obj = itemList.stream().filter(x -> x.getProduct().getName().toLowerCase().equals(nameItem.toLowerCase())).findFirst().orElse(null);
        if (obj != null) {
            itemList.remove(obj);
        } else {
            throw new DomainException("Error: Product of order item not found!");
        }
    }

    // Total




}
