package model.entities;

import model.exceptions.DomainException;

public abstract class Product {

    private String name;
    private Double price;
    private String description;

    public Product(){
    }

    public Product(String name, Double price, String description) {
        setName(name);
        setPrice(price);
        setDescription(description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new DomainException("Error: Name cannot be null!");
        }
        if (name.isEmpty()) {
            throw new DomainException("Error: Name cannot be empty!");
        }
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price == null) {
            throw new DomainException("Error: Price cannot be null");
        }
        if (price <= 0) {
            throw new DomainException("Error: Price cannot be less or equal than zero!");
        }
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new DomainException("Error: Description cannot be null!");
        }
        if (description.isEmpty()) {
            throw new DomainException("Error: Description cannot be empty!");
        }
        this.description = description;
    }

    public void updatePrice(double newPrice) {
        if (newPrice <= 0) {
            throw new DomainException("Error: New price cannot be less or equal than zero");
        }
        this.price = newPrice; // Atualizar de forma dinâmica
    }

    public void updateName(String newName) {
        if (newName == null) {
            throw new DomainException("Error: Name cannot be null!");
        }
        if (newName.isEmpty()) {
            throw new DomainException("Error: Name cannot be empty!");
        }
        this.name = newName; // Atualiza dos dois lados
    }

    @Override
    public String toString() {
        return "Nome do produto: " + name + "\nPreço do prouduto $ " + String.format("%.2f", price);
    }
}
