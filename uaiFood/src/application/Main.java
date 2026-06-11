package application;

import model.entities.*;
import model.exceptions.DomainException;
import model.service.Menu;
import model.service.OrderRepository;
import model.service.OrderService;
import model.service.PaymentService;
import model.util.CheckOrderNumber;
import model.util.PaymentUtil;
import model.util.SimpleValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Menu menu = new Menu();
        List<Product> productList = menu.getProducts();
        OrderService orderService = new OrderService();

        try (Scanner sc = new Scanner(System.in)) {

            System.out.println("\n----- Olá seja bem-vindo ao Uai-Foods -----\n");
            System.out.println("\nPrimeiro vamos iniciar o seu cadastro: ");

            Client client = registerClient(sc);

            System.out.println("\n\nAgora que você já está logado, dê uma olhada no nosso cardápio");
            menu.showMenu();

            System.out.print("\nVocê deseja comprar algum item do nosso cardápio? [s/Sim][n/Não]: ");
            char choiceUser = sc.nextLine().charAt(0);

            if (!SimpleValidation.checkResponseUserWantOrderItem(choiceUser) || choiceUser == 'n') {
                System.out.println("\nObrigado por utilizar!\n---- SAINDO DO SISTEMA ----");
                return;
            }

            List<OrderItem> orderItems = collectOrderItems(sc, productList);

            Order order = orderService.createOrder(client, orderItems);
            System.out.println(order.generateSummary());

            finishOrder(sc, order, orderService);

        } catch (DomainException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error!");
        }
    }

    private static Client registerClient(Scanner sc) {
        System.out.print("Seu nome ou apelido: ");
        String name = sc.nextLine();
        System.out.print("Seu CPF (APENAS NÚMEROS): ");
        String cpf = sc.nextLine();
        return new Client(name, cpf);
    }

    private static List<OrderItem> collectOrderItems(Scanner sc, List<Product> productList) {
        List<OrderItem> orderItems = new ArrayList<>();

        orderItems.add(readOrderItem(sc, productList));

        System.out.print("\nProduto adicionado!\nSeu pedido está quase pronto, deseja continuar comprando? [s/Sim][n/Não]: ");
        char continueChoice = sc.nextLine().charAt(0);

        while (continueChoice == 's' || continueChoice == 'y') {
            System.out.println("\nAdicione os dados do novo item:");
            orderItems.add(readOrderItem(sc, productList));
            System.out.print("\nProduto adicionado!\nSeu pedido está quase pronto, deseja continuar comprando? [s/Sim][n/Não]: ");
            continueChoice = sc.nextLine().charAt(0);
        }

        return orderItems;
    }

    private static OrderItem readOrderItem(Scanner sc, List<Product> productList) {
        System.out.print("Entre com o número do item: ");
        int numOrder = sc.nextInt();
        sc.nextLine();

        if (!CheckOrderNumber.isValidOrderNumber(numOrder)) {
            throw new DomainException("Número de item inválido: " + numOrder);
        }

        System.out.print("\nEntre com a quantidade do produto que você deseja: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        return new OrderItem(productList.get(numOrder), quantity);
    }

    private static void finishOrder(Scanner sc, Order order, OrderService orderService) throws Exception {
        System.out.println(PaymentUtil.message());

        byte choice = sc.nextByte();

        if (!SimpleValidation.checkPaymentChoice(choice)) {
            return;
        }

        System.out.print("\nEntre com um valor: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        PaymentService payService = PaymentUtil.returnPaymentService(choice, amount);
        orderService.processPayment(order, payService);

        System.out.println("\nEntre com o caminho do arquivo para salvar o pedido: ");
        String path = sc.nextLine();

        OrderRepository.saveToFile(order, path);
    }
}