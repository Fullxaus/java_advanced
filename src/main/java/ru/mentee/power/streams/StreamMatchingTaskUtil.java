package ru.mentee.power.streams;

import java.util.List;
import java.util.Optional;

public class StreamMatchingTaskUtil {

    public static Optional<User> findFirstActiveUser(List<User> users) {
        return users.stream()
                .filter(User::isActive)
                .findFirst();
    }


    public static boolean hasUserWithHighLoginCount(List<User> users, int threshold) {
        return users.stream()
                .anyMatch(user -> user.getLoginCount() > threshold);
    }


    public static boolean checkActiveUsersHaveLogins(List<User> users) {
        return users.stream()
                .filter(User::isActive)
                .allMatch(user -> user.getLoginCount() > 0);
    }


    public static boolean checkNoSpamEmails(List<User> users) {
        return users.stream()
                .noneMatch(user -> user.getEmail().endsWith("@spam.com"));
    }
}
