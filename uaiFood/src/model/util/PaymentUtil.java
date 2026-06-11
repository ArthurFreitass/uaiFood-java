package model.util;

import model.exceptions.DomainException;
import model.service.CashPayment;
import model.service.CreditPayment;
import model.service.PaymentService;
import model.service.PixPayment;

public class PaymentUtil {
    public static final String message() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n-----Faça o pagamento para seu pedido ser finalizado-----");
        sb.append("\nEscolha uma forma de pagamento:" +
                "\n[1 - Dinheiro]\n" +
                "[2 - PIX]" +
                "\n[3 - CARTÃO] - ACRÉSCIMO DE 5%\n");
        sb.append("Escolha uma das opções: ");
        return sb.toString();
    }

    //

    public static PaymentService returnPaymentService(int choice, double amount) {
        if (choice == 1) {
            return new CashPayment(amount);
        }
        if (choice == 2) {
            return new PixPayment(amount);
        }
        if (choice == 3) {
            return new CreditPayment(amount);
        }
        throw new DomainException("Error: Invalid method payment!");
    }
}
