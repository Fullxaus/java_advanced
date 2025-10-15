package ru.mentee.power.collections.stacksqueues;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        System.out.println("--- Проверка скобок ---");
        String expr1 = "( [ { } ] )";
        String expr2 = "( [ { ] } )";
        String expr3 = "({[]})()[]";
        String expr4 = "(";
        String expr5 = "])";

        System.out.printf("'%s' сбалансированы? %b\n", expr1, BracketChecker.areBracketsBalanced(expr1));
        System.out.printf("'%s' сбалансированы? %b\n", expr2, BracketChecker.areBracketsBalanced(expr2));
        System.out.printf("'%s' сбалансированы? %b\n", expr3, BracketChecker.areBracketsBalanced(expr3));
        System.out.printf("'%s' сбалансированы? %b\n", expr4, BracketChecker.areBracketsBalanced(expr4));
        System.out.printf("'%s' сбалансированы? %b\n", expr5, BracketChecker.areBracketsBalanced(expr5));

        System.out.println("\n--- Симуляция обработки задач ---");
        List<String> tasks = List.of(
                "Задача A", "Задача B", "Задача C", "Задача D", "Задача E",
                "Задача F", "Задача G", "Задача H", "Задача I", "Задача J"
        );
        int maxConcurrent = 3;
        TaskScheduler.simulateTaskProcessing(tasks, maxConcurrent);
    }
}
