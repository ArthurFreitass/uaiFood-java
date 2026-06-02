package model.service;

import model.exceptions.DomainException;

public class CreditPayment implements PaymentService {

    private Double amountReceived;
    private static final double TAX = 0.05;

    public CreditPayment(Double amountReceived) {
        if (amountReceived <= 0) {
            throw new DomainException("Error: Payment amount cannot be less or than equal zero!");
        }
        this.amountReceived = amountReceived;
    }

    @Override
    public double processPayment(double totalOrder) {
        if (amountReceived < (totalOrder + (totalOrder * TAX))) {
            throw new DomainException("Error: the amount received for payment is less than the total price of the order");
        }
        return amountReceived - totalOrder;
    }
}
