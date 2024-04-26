package ru.job4j.ood.isp.menu;

import java.util.Iterator;
import java.util.List;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Menu.MenuItemInfo menuItem = iterator.next();
            System.out.println(menuItem.getNumber() + " " + menuItem.getName());
        }
    }
}
