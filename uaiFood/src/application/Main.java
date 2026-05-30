package application;

import model.entities.*;
import model.entities.enums.OrderStatus;
import model.exceptions.DomainException;
import model.service.Menu;
import model.util.CheckOrderNumber;
import model.util.SimpleValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Menu menu = new Menu();

        // Adicionar todos os pedidos do menu a os produtos
        List<Product> productList = menu.getProducts();

        try (Scanner sc = new Scanner(System.in)) {

            System.out.println("\n----- Olá seja bem-vindo ao Uai-Foods -----\n");
            System.out.println("\nPrimeiro vamos iniciar o seu cadastro: ");

            System.out.print("Seu nome ou apelido: ");
            String nameClient = sc.nextLine();
            System.out.print("Seu CPF (APENAS NÚMEROS): ");
            String cpf = sc.nextLine();

            // Instância o Cliente
            Client client = new Client(nameClient, cpf);

            System.out.println("\n\nAgora que você já está logado, dê uma olhada no nosso cardápio");

            menu.showMenu();

            System.out.print("\nVocê deseja comprar algum item do nosso cardápio? [s/Sim][n/Não]: ");
            char choiceUser = sc.nextLine().charAt(0);

            boolean isValid = SimpleValidation.checkResponseUserWantOrderItem(choiceUser);

            if ((isValid) && choiceUser == 's' || choiceUser == 'y') {

                List<OrderItem> orderItems = new ArrayList<>();

                System.out.print("Entre com o número do item: ");
                int numOrder = sc.nextInt();
                sc.nextLine();

                if (CheckOrderNumber.isValidOrderNumber(numOrder)) {
                    System.out.print("\nEntre com a quantidade do produto que você deseja: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();

                    orderItems.add(new OrderItem(productList.get(numOrder), quantity));
                }

                System.out.print("\nProduto adicionado!\nSeu pedido está quase pronto, deseja continuar comprando? [s/Sim][n/Não]: ");
                char newChoice = sc.nextLine().charAt(0);

                if ((SimpleValidation.checkResponseUserWantOrderItem(newChoice)) && choiceUser == 's' || choiceUser == 'y') {
                    while (newChoice == 's' || newChoice == 'y') {
                        System.out.println("\nAdicione os dados do novo item:");
                        System.out.print("Entre com o número do item: ");
                        numOrder = sc.nextInt();
                        sc.nextLine();

                        if (CheckOrderNumber.isValidOrderNumber(numOrder)) {
                            System.out.print("\nEntre com a quantidade do produto que você deseja: ");
                            int quantity = sc.nextInt();
                            sc.nextLine();
                            orderItems.add(new OrderItem(productList.get(numOrder), quantity));
                            System.out.print("\nProduto adicionado!\nSeu pedido está quase pronto, deseja continuar comprando? [s/Sim][n/Não]: ");
                            newChoice = sc.nextLine().charAt(0);
                        }
                    }

                    Order order = new Order((int) (Math.random() * 1000), client, OrderStatus.PENDING);
                    if (orderItems.size() > 1) {
                        for (OrderItem o : orderItems) {
                            order.addItem(o);
                        }
                    }
                    System.out.println(order.generateSummary());
                } else {
                    Order order = new Order(101, client, OrderStatus.PENDING);
                    order.addItem(orderItems.get(0));
                    System.out.println(order.generateSummary());
                }
            } else { // Pedido não feito
                System.out.println("\nObrigado por utilizar!\n---- SAINDO DO SISTEMA ----");
            }

        } catch (
                DomainException e) {
            System.out.println(e.getMessage());
        } catch (
                RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error!");
        }
    }
}