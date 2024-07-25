package ru.job4j.ood.isp.menu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class PrinterTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void whenPrints() {
        Printer printer = new Printer();
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "One", () -> { });
        menu.add("One", "two", () -> { });
        menu.add(null, "three", () -> { });
        menu.add("two", "second", () -> { });
        menu.add("second", "third", () -> { });
        printer.print(menu);
        assertThat("1. One" + System.lineSeparator() +
                "----1.1. two" + System.lineSeparator() +
                "------1.1.1. second" + System.lineSeparator() +
                "--------1.1.1.1. third" + System.lineSeparator() +
                "2. three").isEqualTo(outputStreamCaptor.toString().trim());
    }
}