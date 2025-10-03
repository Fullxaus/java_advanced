package ru.mentee.power.oop.encapsulation;

import java.time.LocalDate;
import java.util.Objects;

public class UserProfile {
    private final int userId;
    private String username;
    private String email;
    private final LocalDate registrationDate;

    public UserProfile(int userId, String username, String email) {
        this.userId = userId;
        this.registrationDate = LocalDate.now();
        // Используем сеттеры, чтобы применить валидацию сразу
        setUsername(username);
        setEmail(email);
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            // Выбираем поведение: кидаем исключение, чтобы явно сигнализировать об ошибке
            throw new IllegalArgumentException("username не может быть null или пустым");
            // Альтернатива: System.err.println("username не может быть null или пустым");
        }
        this.username = username.trim();
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email должен быть не null и содержать символ '@'");
            // Альтернатива: System.err.println("email должен быть не null и содержать символ '@'");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
