package model.service;

import model.entities.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OrderRepository {

    public static void saveToFile(Order o, String path) throws IOException {
        try (FileWriter fw = new FileWriter(path, true); // Para não sobrescrever o que já está salvo
        BufferedWriter bw = new BufferedWriter(fw)) {
            String content = o.generateSummary();
            bw.write(content);
            bw.write("\n\n=====================================");
            System.out.println("Pedido Concluído com sucesso!");
        } catch (IOException e) {
            System.out.println("\nErro ao tentar salvar o arquivo, verifique se o caminho: "+ path+ " está correto!");
            System.out.println("\n" + e.getMessage());
        }
    }
}
