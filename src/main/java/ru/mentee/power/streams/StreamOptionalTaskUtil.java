package ru.mentee.power.streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamOptionalTaskUtil {

    /**
     * Найти пользователя по username (регистронезависимо).
     *
     * @param users    список пользователей
     * @param username имя пользователя для поиска
     * @return Optional<User> пользователь с указанным username, если найден; иначе пустой Optional
     */
    public static Optional<User> findUserByUsername(List<User> users, String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    /**
     * Получить email пользователя по username. Если пользователь не найден, вернуть defaultEmail.
     *
     * @param users         список пользователей
     * @param username      имя пользователя для поиска
     * @param defaultEmail  email по умолчанию, если пользователь не найден
     * @return String email пользователя или defaultEmail, если пользователь не найден
     */
    public static String getUserEmail(List<User> users, String username, String defaultEmail) {
        return findUserByUsername(users, username)
                .map(User::getEmail)
                .orElse(defaultEmail);
    }

    /**
     * Найти пользователя по username, и если он найден и активен (isActive), вывести его loginCount на консоль.
     * Если не найден или неактивен, вывести сообщение об этом.
     *
     * @param users    список пользователей
     * @param username имя пользователя для поиска
     */
    public static void printActiveLoginCount(List<User> users, String username) {
        Optional<User> userOpt = findUserByUsername(users, username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.isActive()) {
                System.out.println("Login count for " + username + ": " + user.getLoginCount());
            } else {
                System.out.println(username + " найден, но не активен.");
            }
        } else {
            System.out.println(username + " не найден.");
        }
    }

    /**
     * Найти список username пользователей, у которых loginCount больше или равен minLoginCount.
     *
     * @param users           список пользователей
     * @param minLoginCount минимальное количество логинов
     * @return List<String> список username пользователей с высоким количеством логинов
     */
    public static List<String> findUsernamesWithHighLoginCount(List<User> users, int minLoginCount) {
        return users.stream()
                .filter(user -> user.getLoginCount() >= minLoginCount)
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
}

