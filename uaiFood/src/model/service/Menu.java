package model.service;

import model.entities.Product;
import model.entities.Food;
import model.entities.Drink;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Product> products = new ArrayList<>(); // Atributo da classe

    public Menu() {
        generateMenu();
    }

    private void generateMenu() {
        products.add(new Food("X-Burger", 25.90, "Hambúrguer clássico \nPão carne 160g queijo"));
        products.add(new Food("X-Salada", 28.50, "Hambúrguer com salada \nPão carne alface tomate queijo"));
        products.add(new Food("X-Bacon", 32.00, "Para amantes de bacon \nPão carne muito bacon queijo"));
        products.add(new Food("Batata Frita G", 18.00, "Porção generosa \nBatatas selecionadas sal"));
        products.add(new Food("Nuggets (10un)", 22.00, "Frango crocante \nPeito de frango empanado"));
        products.add(new Food("Pizza Brotinho", 35.00, "Pizza individual \nMassa artesanal mussarela molho"));
        products.add(new Food("Hot Dog Especial", 15.50, "Cachorro quente completo \nPão 2 salsichas purê batata palha"));
        products.add(new Food("Coxinha de Frango", 8.50, "Salgado clássico \nMassa de batata frango desfiado"));
        products.add(new Food("Pastel de Carne", 10.00, "Frito na hora \nCarne moída temperada massa crocante"));
        products.add(new Food("Sanduíche Natural", 19.90, "Opção leve \nPão integral frango cenoura maionese"));
        // Drinks
        products.add(new Drink("Coca-Cola Lata", 6.50, "Refrigerante \nLata 350ml"));
        products.add(new Drink("Guaraná Antarctica", 6.00, "Refrigerante \nLata 350ml"));
        products.add(new Drink("Suco de Laranja", 12.00, "Suco Natural \nCopo 500ml"));
        products.add(new Drink("Água Mineral", 4.00, "Sem gás \nGarrafa 500ml"));
        products.add(new Drink("Cerveja Heineken", 14.00, "Long neck \n330ml"));
        products.add(new Drink("Chá Gelado", 9.00, "Limão ou Pêssego \n400ml"));
        products.add(new Drink("Milkshake Baunilha", 18.50, "Gelado e cremoso \n400ml"));
        products.add(new Drink("Energético", 15.00, "Red Bull \n250ml"));
        products.add(new Drink("Vinho Taça", 22.00, "Tinto seco \n150ml"));
        products.add(new Drink("Café Expresso", 5.50, "Grãos selecionados \n50ml"));
    }

    public List<Product> getProducts() {
        return products;
    }

    public void showMenu() {
        System.out.println("\n========== CARDÁPIO UAIFOOD  ==========");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);

            // Identifica se é comida ou bebida para exibir a unidade (G ou ML)
            String unit = "";
            if (p instanceof Food) unit = ((Food) p).getTypeProduct().toString();
            if (p instanceof Drink) unit = ((Drink) p).getTypeProduct().toString();

            System.out.println("[" + String.format("%02d", i) + "] " + p.getName() + " (" + unit + ")");
            System.out.println("Preço: R$ " + String.format("%.2f", p.getPrice()));
            System.out.println("Info: " + p.getDescription());
            System.out.println("--------------------------------------------");
        }
    }
}