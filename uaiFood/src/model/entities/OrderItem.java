package model.entities;

import model.exceptions.DomainException;

public class OrderItem {

    private Product product;
    private Integer quantity;

    public OrderItem() {
    }

    public OrderItem(Product p, Integer quantity) {
        this.product = p;
        setQuantity(quantity);
    }

    public Product getProduct() {
        return product; // Retorna a referência
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity <= 0) {
            throw new DomainException("Error: Quantity cannot be zero!");
        }
        this.quantity = quantity;
    }

    public double subTotal() {
        return product.getPrice() * quantity;
    }
}
