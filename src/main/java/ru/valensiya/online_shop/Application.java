package ru.valensiya.online_shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        Cart cart = context.getBean(Cart.class);
        System.out.println("Добро пожаловать в магазин");
        printCommand();
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            command = scanner.nextLine();
            if (command.equals("ctlg")) {
                printCatalog(productRepository);
            } else if (command.equals("help")) {
                printCommand();
            } else if (command.startsWith("add")) {
                int id = Integer.parseInt(command.substring(4));
                cart.addProduct(productRepository.getProductById(id));
            } else if (command.startsWith("del")) {
                int id = Integer.parseInt(command.substring(4));
                cart.deleteProduct(productRepository.getProductById(id));
            } else if(!command.equals("exit")) {
                System.out.println("Такой команды нет. Список доступных команд можно посмотреть с помощью help");
            }

        } while (!command.equals("exit"));

    }

    private static void printCommand() {
        System.out.println("Для просмотра каталога товаров введите команду ctlg");
        System.out.println("Для добавления товара в корзину введите команду add и id товара (Например: add 1)");
        System.out.println("Для удаления товара из корзины введите команду del и id товара (Например: del 1)");
        System.out.println("Для просмотра возможных команд введите help");
        System.out.println("Для выхода введите exit");
    }

    private static void printCatalog(ProductRepository productRepository) {
        System.out.println("У нас имеются следующие товары: ");
        List<Product> productList = productRepository.getProducts();
        for(Product p : productList) {
            System.out.println(p.toString());
        }
    }
}
