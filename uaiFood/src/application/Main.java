package application;

import model.entities.Food;
import model.entities.OrderItem;
import model.entities.Product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            OrderItem orderItem = new OrderItem((new Food("Macarrão", 10.0, "Macarrão a bolonhesa")), 10);
        }
    }
}
