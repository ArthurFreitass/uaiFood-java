package model.entities;

import model.entities.enums.OrderStatus;
import model.exceptions.DomainException;
import model.service.PaymentService;
import model.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int number;

    private Client client;
    private List<OrderItem> itemList;
    private OrderStatus status;

    public Order() {
    }

    public Order(Integer number, Client client, OrderStatus status) {
        setNumber(number);
        this.client = client;
        this.status = status;
        itemList = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public Client getClient() {
        return client;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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

    public double total() {
        double sum = 0;

        for (OrderItem o : itemList) {
            sum += o.getQuantity() * o.getProduct().getPrice();
        }
        return sum;
    }

    public String generateSummary() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nSeu pedido foi feito!\n\n");
        sb.append("Confira os dados do seu pedido:\n\n");
        sb.append("Dados Gerais:\n");
        sb.append("NOME DO CLIENTE: "+ client.getName() +"\n");
        sb.append("NÚMERO: "+ number +"\n");
        sb.append("Items do pedido: \n\n");

        for (OrderItem o : itemList) {
            sb.append(o.getProduct().getName() + " Subtotal $ " + String.format("%.2f", o.subTotal()) + "\n");
        }

        sb.append("Total a pagar $ "+ String.format("%.2f", total())+ "\n\n");

        sb.append("STATUS = " + status);
        if (status == OrderStatus.FINISHED) {
            sb.append("\nPAGAMENTO CONCLUÍDO!\n");
        }
        sb.append("\nHORÁRIO E DATA: [ dia/mês/ano ] "+ LocalDateTime.now().format(DateTimeUtil.fmt()));
        return sb.toString();
    }

    public double payment(PaymentService payService) {
        double change = payService.processPayment(total());
        status = OrderStatus.FINISHED;
        System.out.println("PAGAMENTO ACEITO!");
        return change;
    }
}
