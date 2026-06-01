package model.entities;

import model.entities.enums.TypeProduct;

public class Drink extends Product {

    private TypeProduct typeProduct = TypeProduct.ML;

    public Drink(String name, Double price, String description) {
        super(name, price, description);
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }
}
