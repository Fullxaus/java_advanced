package ru.mentee.power.collections.stacksqueues;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Map;

public class BracketChecker {

    private static final Map<Character, Character> BRACKET_PAIRS = Map.of(
            ')', '(',
            '}', '{',
            ']', '['
    );

    /**
     * Проверяет сбалансированность скобок в строке.
     *
     * @param expression Строка с выражением.
     * @return true, если скобки сбалансированы, иначе false.
     */
    public static boolean areBracketsBalanced(String expression) {
        if (expression == null) {
            return true;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : expression.toCharArray()) {
            // Открывающие скобки: '(', '{', '['
            if (BRACKET_PAIRS.containsValue(ch)) {
                stack.push(ch);
            }
            // Закрывающие скобки: ')', '}', ']'
            else if (BRACKET_PAIRS.containsKey(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (top != BRACKET_PAIRS.get(ch)) {
                    return false;
                }
            }
            // Игнорируем прочие символы
        }

        return stack.isEmpty();
    }
}
