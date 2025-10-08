package ru.mentee.power.oop.exceptions_intro.profile;

public class ProfileExceptionDemo {
    public static void main(String[] args) {
        // 1) Корректный профиль
        try {
            UserProfile good = new UserProfile(1, "Alice", "alice@example.com", 30);
            System.out.println("Создан корректный профиль: " + good);

            // пробуем корректно изменить поля
            try {
                good.setUsername(" Alice Cooper ");
                good.setEmail("alice.cooper@example.org");
                good.setAge(31);
                System.out.println("После корректных изменений: " + good);
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка при корректном изменении (неожиданно): " + e.getMessage());
            }

            // пробуем установить некорректные значения через сеттеры
            try {
                good.setUsername("   ");
            } catch (IllegalArgumentException e) {
                System.out.println("Поймано при setUsername: " + e.getMessage());
            }

            try {
                good.setEmail("alice.example.com");
            } catch (IllegalArgumentException e) {
                System.out.println("Поймано при setEmail: " + e.getMessage());
            }

            try {
                good.setAge(200);
            } catch (IllegalArgumentException e) {
                System.out.println("Поймано при setAge: " + e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка при создании корректного профиля (неожиданно): " + e.getMessage());
        }

        // 2) Невалидные профили для демонстрации ошибок конструктора
        createProfileSafe(-1, "Bob", "bob@example.com", 25);             // неверный ID
        createProfileSafe(2, "   ", "charlie@example.com", 40);          // пустое имя
        createProfileSafe(3, "Charlie", "charlie.example.com", 40);     // некорректный email
        createProfileSafe(4, "Dave", "dave@example.com", -5);           // некорректный возраст
        createProfileSafe(5, null, null, 50);                           // несколько ошибок
    }

    private static void createProfileSafe(int id, String username, String email, int age) {
        try {
            UserProfile p = new UserProfile(id, username, email, age);
            System.out.println("Успешно создан: " + p);
        } catch (IllegalArgumentException e) {
            System.out.println("Не удалось создать профиль (id=" + id + "): " + e.getMessage());
        }
    }
}

