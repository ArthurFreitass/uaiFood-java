package model.service;

import model.exceptions.DomainException;

public class CashPayment implements PaymentService {

    private Double amountReceived;

    public CashPayment(Double amountReceived) {
        if (amountReceived <= 0) {
            throw new DomainException("Error: Payment amount cannot be less or than equal zero!");
        }
        this.amountReceived = amountReceived;
    }

    @Override
    public double processPayment(double totalOrder) {
        if (amountReceived < totalOrder) {
            throw new DomainException("Error: the amount received for payment is less than the total price of the order");
        }
        return amountReceived - totalOrder;
    }
}
