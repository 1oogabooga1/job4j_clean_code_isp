package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

public class TodoApp {
    private Menu menu;

    private MenuPrinter printer = new Printer();

    private final ActionDelegate defaultAction = () -> System.out.println("Some action");

    public TodoApp(Menu menu) {
        this.menu = menu;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("1. Добавить элемент в корень меню");
            System.out.println("2. Добавить дочерний элемент");
            System.out.println("3. Вызвать действие, привязанное к пункту мен");
            System.out.println("4. Вывести меню в консоль");
            System.out.println("5. Завершить программу");
            System.out.print("Выбрать: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addElementToRoot(scanner);
                case "2" -> addElementToParent(scanner);
                case "3" -> invokeAction(scanner);
                case "4" -> printer.print(menu);
                case "5" -> {
                    run = false;
                }
                default -> System.out.println("Такого пункта не сущетсвует, попробуйте еще раз");
            }
        }
    }

    private void addElementToRoot(Scanner scanner) {
        System.out.print("Введите имя элемента: ");
        String name = scanner.nextLine();
        menu.add(Menu.ROOT, name, defaultAction);
    }

    private void addElementToParent(Scanner scanner) {
        System.out.print("Введите имя родительского элемента: ");
        String parentName = scanner.nextLine();
        System.out.print("Введите имя дочернего элемента: ");
        String childName = scanner.nextLine();
        menu.add(parentName, childName, defaultAction);
    }

    private void invokeAction(Scanner scanner) {
        System.out.print("Введите название элемента меню: ");
        String itemName = scanner.nextLine();
        Optional<Menu.MenuItemInfo> menuItemInfo = menu.select(itemName);
        menuItemInfo.ifPresent(itemInfo -> itemInfo.getActionDelegate().delegate());
    }

    public static void main(String[] args) {
        Menu menu1 = new SimpleMenu();
        TodoApp app = new TodoApp(menu1);
        app.showMenu();
    }
}
