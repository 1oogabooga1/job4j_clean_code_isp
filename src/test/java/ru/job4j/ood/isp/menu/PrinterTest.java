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
        printer.print(menu);
        assertThat("""
                1. One
                1.1. two
                2. three""").isEqualTo(outputStreamCaptor.toString().trim());
    }
}