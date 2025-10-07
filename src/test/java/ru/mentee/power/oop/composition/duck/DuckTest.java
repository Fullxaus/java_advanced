package ru.mentee.power.oop.composition.duck;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.oop.composition.behavior.FlyBehavior;
import ru.mentee.power.oop.composition.behavior.QuackBehavior;
import ru.mentee.power.oop.composition.behavior.impl.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для Уток и их Поведения")
public class DuckTest {
    private Duck mallardDuck;
    private Duck modelDuck;
    private Duck rubberDuck;
    private Duck decoyDuck;

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        mallardDuck = new MallardDuck();
        modelDuck = new ModelDuck();
        rubberDuck = new RubberDuck();
        decoyDuck = new DecoyDuck();

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("MallardDuck должна использовать FlyWithWings и Quack")
    void mallardDuckShouldUseCorrectBehaviors() {
        // проверяем типы стратегий
        assertThat(mallardDuck.flyBehavior).isInstanceOf(FlyWithWings.class);
        assertThat(mallardDuck.quackBehavior).isInstanceOf(Quack.class);

        // проверяем вывод при выполнении
        mallardDuck.performFly();
        mallardDuck.performQuack();

        String out = outContent.toString().trim();
        assertThat(out).contains("I'm flying with wings").contains("I'm flying with wings!");
        assertThat(out).contains("Quack");
    }

    @Test
    @DisplayName("ModelDuck должна изначально использовать FlyNoWay")
    void modelDuckShouldInitiallyNotFly() {
        assertThat(modelDuck.flyBehavior).isInstanceOf(FlyNoWay.class);
        modelDuck.performFly();
        String out = outContent.toString().trim();
        assertThat(out).contains("I can't fly").contains("I can't fly.");
    }


    @Test
    @DisplayName("Должна быть возможность динамически менять поведение кряканья")
    void shouldAllowDynamicChangeOfQuackBehavior() {
        // RubberDuck изначально пищит (Squeak)
        assertThat(rubberDuck.quackBehavior).isInstanceOf(Squeak.class);
        rubberDuck.performQuack();
        String before = outContent.toString();

        // сменим поведение на молчание
        rubberDuck.setQuackBehavior(new MuteQuack());
        rubberDuck.performQuack();
        String after = outContent.toString();

        assertThat(before).containsIgnoringCase("squeak");
        assertThat(after).contains("Silence").containsIgnoringCase("silence");
    }

    @Test
    @DisplayName("RubberDuck и DecoyDuck должны иметь специфичные поведения")
    void rubberAndDecoyBehaviors() {
        assertThat(rubberDuck.flyBehavior).isInstanceOf(FlyNoWay.class);
        assertThat(rubberDuck.quackBehavior).isInstanceOf(Squeak.class);

        assertThat(decoyDuck.flyBehavior).isInstanceOf(FlyNoWay.class);
        assertThat(decoyDuck.quackBehavior).isInstanceOf(MuteQuack.class);

        // Проверка вывода
        rubberDuck.performFly();
        rubberDuck.performQuack();
        decoyDuck.performFly();
        decoyDuck.performQuack();

        String out = outContent.toString();
        assertThat(out).containsIgnoringCase("squeak");
        assertThat(out).contains("Silence").containsIgnoringCase("silence");
        assertThat(out).contains("I can't fly").contains("I can't fly.");
    }

}
