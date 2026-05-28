package application;

import model.entities.*;
import model.entities.enums.OrderStatus;
import model.exceptions.DomainException;
import model.service.Menu;
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

            System.out.print("Você deseja comprar algum item do nosso cardápio? [s/Sim][n/Não]: ");
            char choiceUser = sc.nextLine().charAt(0);

            boolean isValid = SimpleValidation.checkResponseUserWantOrderItem(choiceUser);

            if (isValid) {
                System.out.println("");
            }

        } catch (DomainException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
