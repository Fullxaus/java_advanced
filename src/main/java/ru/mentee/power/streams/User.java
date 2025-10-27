package ru.mentee.power.streams;

 public class User {
    private String username;
    private String email;
    private boolean isActive;
    private int loginCount;

    public User(String username, String email, boolean isActive, int loginCount) {
        this.username = username;
        this.email = email;
        this.isActive = isActive;
        this.loginCount = loginCount;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getLoginCount() {
        return loginCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", loginCount=" + loginCount +
                '}';
    }
}
