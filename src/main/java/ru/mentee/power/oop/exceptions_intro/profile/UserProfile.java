package ru.mentee.power.oop.exceptions_intro.profile;

public class UserProfile {
    private final int userId;
    private String username;
    private String email;
    private int age;

    public UserProfile(int userId, String username, String email, int age) {
        if (userId <= 0) {
            throw new IllegalArgumentException("ID пользователя должен быть положительным.");
        }
        validateUsername(username);
        validateEmail(email);
        validateAge(age);

        this.userId = userId;
        this.username = username.trim();
        this.email = email.trim();
        this.age = age;
    }

    private void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым.");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Email указан некорректно.");
        }
    }

    private void validateAge(int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Возраст должен быть в диапазоне от 0 до 120.");
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        validateUsername(username);
        this.username = username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}

