package model.entities;

import model.entities.enums.TypeProduct;
import model.exceptions.DomainException;

public class Food extends Product {

    private TypeProduct typeProduct = TypeProduct.G;

    public Food(String name, Double price, String description) {
        super(name, price, description);
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }
}
