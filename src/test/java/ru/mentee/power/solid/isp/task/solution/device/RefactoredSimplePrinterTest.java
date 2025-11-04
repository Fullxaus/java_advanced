package ru.mentee.power.solid.isp.task.solution.device;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.solid.isp.task.solution.interf.Printable;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;

public class RefactoredSimplePrinterTest {
    private final RefactoredSimplePrinter printer = new RefactoredSimplePrinter();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach void setUpStreams() { System.setOut(new PrintStream(outContent)); }
    @AfterEach void restoreStreams() { System.setOut(originalOut); }

    @Test
    @DisplayName("RefactoredSimplePrinter должен реализовывать Printable")
    void shouldImplementPrintable() {
        assertThat(printer).isInstanceOf(Printable.class);
    }

    @Test
    @DisplayName("RefactoredSimplePrinter НЕ должен реализовывать Scannable, Faxable, Staplable")
    void shouldNotImplementOtherInterfaces() {
        // Эти проверки безопасно оставлять, если интерфейсы созданы в проекте.
        try {
            Class<?> scannable = Class.forName("ru.mentee.power.solid.isp.task.solution.interf.Scannable");
            assertThat(scannable.isInstance(printer)).isFalse();
        } catch (ClassNotFoundException ignored) { /* пропускаем, если интерфейс отсутствует */ }

        try {
            Class<?> faxable = Class.forName("ru.mentee.power.solid.isp.task.solution.interf.Faxable");
            assertThat(faxable.isInstance(printer)).isFalse();
        } catch (ClassNotFoundException ignored) { /* пропускаем */ }

        try {
            Class<?> staplable = Class.forName("ru.mentee.power.solid.isp.task.solution.interf.Staplable");
            assertThat(staplable.isInstance(printer)).isFalse();
        } catch (ClassNotFoundException ignored) {
             }
    }

    @Test
    @DisplayName("print() должен выводить корректное сообщение")
    void printShouldOutputCorrectMessage() {
        printer.print("test_doc_simple");
        assertThat(outContent.toString()).contains("RefactoredSimplePrinter: Печать документа - test_doc_simple");
    }
}
