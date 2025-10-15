package ru.mentee.power.collections.stacksqueues;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для BracketChecker")
public class BracketCheckerTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "()",
            "[]",
            "{}",
            "()[]{}",
            "([{}])",
            "({[]})(){}",
            "(((())))[]",
            "Текст (с [вложенными] скобками {и без}) абсолютно корректен."
    })
    @DisplayName("Должен возвращать true для сбалансированных скобок")
    void shouldReturnTrueForBalancedBrackets(String expression) {
        assertThat(BracketChecker.areBracketsBalanced(expression)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "(",
            ")",
            "(()",
            "())(",
            "([)]",
            "{(})",
            "[",
            "}{ A }",
            "( [ { ] } )"
    })
    @DisplayName("Должен возвращать false для несбалансированных скобок")
    void shouldReturnFalseForUnbalancedBrackets(String expression) {
        assertThat(BracketChecker.areBracketsBalanced(expression)).isFalse();
    }

    @Test
    @DisplayName("Должен возвращать true для строки без скобок")
    void shouldReturnTrueForStringWithoutBrackets() {
        String expr = "Просто текст без скобок и цифр 12345.";
        assertThat(BracketChecker.areBracketsBalanced(expr)).isTrue();
    }

    @Test
    @DisplayName("Должен возвращать true для пустой строки")
    void shouldReturnTrueForEmptyString() {
        assertThat(BracketChecker.areBracketsBalanced("")).isTrue();
    }

    @Test
    @DisplayName("Должен возвращать true (или как определено) для null")
    void shouldHandleNullInput() {
        // В реализации выше null рассматривается как корректный (true).
        assertThat(BracketChecker.areBracketsBalanced(null)).isTrue();
    }

}
