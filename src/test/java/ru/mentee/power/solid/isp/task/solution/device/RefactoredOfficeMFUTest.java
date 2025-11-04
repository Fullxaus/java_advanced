package ru.mentee.power.solid.isp.task.solution.device;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.solid.isp.task.solution.interf.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;

public class RefactoredOfficeMFUTest {
    private final RefactoredOfficeMFU mfu = new RefactoredOfficeMFU();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach void setUpStreams() { System.setOut(new PrintStream(outContent)); }
    @AfterEach void restoreStreams() { System.setOut(originalOut); }

    @Test
    @DisplayName("RefactoredOfficeMFU должен реализовывать Printable, Scannable, Faxable")
    void shouldImplementCorrectInterfaces() {
        assertThat(mfu).isInstanceOf(Printable.class);
        assertThat(mfu).isInstanceOf(Scannable.class);
        assertThat(mfu).isInstanceOf(Faxable.class);
    }

    @Test
    @DisplayName("RefactoredOfficeMFU НЕ должен реализовывать Staplable")
    void shouldNotImplementStaplable() {
        try {
            Class<?> staplable = Class.forName("ru.mentee.power.solid.isp.task.solution.interf.Staplable");
            assertThat(staplable.isInstance(mfu)).isFalse();
        } catch (ClassNotFoundException ignored) { /* пропускаем если Staplable отсутствует */ }
    }

    @Test
    @DisplayName("print() должен выводить корректное сообщение")
    void printShouldOutputCorrectMessage() {
        mfu.print("office_print");
        assertThat(outContent.toString()).contains("RefactoredOfficeMFU: Печать - office_print");
    }

    @Test
    @DisplayName("scan() должен выводить корректное сообщение")
    void scanShouldOutputCorrectMessage() {
        outContent.reset();
        mfu.scan("office_scan");
        assertThat(outContent.toString()).contains("RefactoredOfficeMFU: Сканирование - office_scan");
    }

    @Test
    @DisplayName("fax() должен выводить корректное сообщение")
    void faxShouldOutputCorrectMessage() {
        outContent.reset();
        mfu.fax("office_fax");
        assertThat(outContent.toString()).contains("RefactoredOfficeMFU: Отправка факса - office_fax");
    }
}
