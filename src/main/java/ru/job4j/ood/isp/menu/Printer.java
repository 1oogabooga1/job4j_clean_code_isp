package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItem : menu) {
            String[] splitNumber = menuItem.getNumber().split("\\.");
            System.out.println(dash(splitNumber) + menuItem.getNumber() + " " + menuItem.getName());
        }
    }

    private String dash(String[] splitNumber) {
        int count = 0;
        for (String s : splitNumber) {
            if (Character.isDigit(s.charAt(0))) {
                count++;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (count != 1) {
            sb.append("--".repeat(Math.max(0, count)));
        }
        return sb.toString();
    }
}
