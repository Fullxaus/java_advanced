package ru.mentee.power.oop.encapsulation;

public class ProfileDemo {
    public static void main(String[] args) {
        // Создаём корректные профили
        UserProfile u1 = new UserProfile(1, "alice", "alice@example.com");
        UserProfile u2 = new UserProfile(2, "  bob  ", "bob@example.org"); // пробелы в имени -> trim()

        // Вывод с помощью toString и геттеров
        System.out.println(u1);
        System.out.println("ID: " + u1.getUserId() + ", username: " + u1.getUsername() + ", email: " + u1.getEmail() + ", registered: " + u1.getRegistrationDate());

        System.out.println(u2);
        System.out.println("ID: " + u2.getUserId() + ", username: " + u2.getUsername() + ", email: " + u2.getEmail() + ", registered: " + u2.getRegistrationDate());

        // Попытки изменить имя и email валидными значениями
        u1.setUsername("alice_new");
        u1.setEmail("alice_new@example.com");
        System.out.println("После изменения: " + u1);

        // Попытки установить невалидные значения — ожидаем IllegalArgumentException
        try {
            u2.setUsername("   "); // невалидно
        } catch (IllegalArgumentException ex) {
            System.err.println("Ошибка при установке username: " + ex.getMessage());
        }

        try {
            u2.setEmail("invalid-email"); // невалидно, нет '@'
        } catch (IllegalArgumentException ex) {
            System.err.println("Ошибка при установке email: " + ex.getMessage());
        }

        System.out.println("Итоговый профиль u2: " + u2);

        // Попытка напрямую изменить приватное поле (пример в комментарии — не будет компилироваться)
        // u1.username = "hacker"; // ОШИБКА: username имеет доступ private
    }
}
