package ru.mentee.power.streams;

import java.util.List;
import java.util.Optional;

public class StreamMatchingTask {

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alice", "alice@example.com", true, 55),
                new User("bob", "bob@example.com", false, 20),
                new User("charlie", "charlie@sample.net", true, 105),
                new User("david", "david@spam.com", true, 0),
                new User("eve", "eve@example.com", true, 99)
        );

        System.out.println("--- Исходный список пользователей ---");
        users.forEach(System.out::println);

        System.out.println("\n--- Поиск первого активного пользователя --- ");
        Optional<User> firstActiveUser = StreamMatchingTaskUtil.findFirstActiveUser(users);
        firstActiveUser.ifPresentOrElse(
                user -> System.out.println("Первый активный пользователь: " + user.getUsername()),
                () -> System.out.println("Активных пользователей не найдено")
        );

        List<User> noActiveUsers = List.of(new User("inactive", "test@test.com", false, 10));
        StreamMatchingTaskUtil.findFirstActiveUser(noActiveUsers).ifPresentOrElse(
                user -> System.out.println("Найден активный в списке noActiveUsers: " + user.getUsername()),
                () -> System.out.println("Активных в noActiveUsers не найдено.")
        );

        System.out.println("\n--- Проверка loginCount > 100 --- ");
        boolean hasUserWithHighLoginCount = StreamMatchingTaskUtil.hasUserWithHighLoginCount(users, 100);
        System.out.println("Есть пользователь с loginCount > 100? " + hasUserWithHighLoginCount);
        boolean hasUserWithExtremeLoginCount = StreamMatchingTaskUtil.hasUserWithHighLoginCount(users, 1000);
        System.out.println("Есть пользователь с loginCount > 1000? " + hasUserWithExtremeLoginCount);


        System.out.println("\n--- Проверка: Все активные имеют loginCount > 0? --- ");
        boolean allActiveHaveLogins = StreamMatchingTaskUtil.checkActiveUsersHaveLogins(users);
        System.out.println("Все активные пользователи имеют loginCount > 0? " + allActiveHaveLogins);

        List<User> allActiveWithLogins = List.of(new User("goodUser", "good@example.com", true, 10));
        boolean allActiveWithLoginsCheck = StreamMatchingTaskUtil.checkActiveUsersHaveLogins(allActiveWithLogins);
        System.out.println("Все активные (в другом списке) имеют loginCount > 0? " + allActiveWithLoginsCheck);

        System.out.println("\n--- Проверка: Нет пользователей с email @spam.com? --- ");
        boolean noSpamUsers = StreamMatchingTaskUtil.checkNoSpamEmails(users);
        System.out.println("Нет пользователей с email @spam.com? " + noSpamUsers);

        List<User> noSpamList = List.of(new User("cleanUser", "clean@example.com", true, 10));
        boolean noSpamUsersInCleanList = StreamMatchingTaskUtil.checkNoSpamEmails(noSpamList);
        System.out.println("Нет пользователей с email @spam.com в списке noSpamList? " + noSpamUsersInCleanList);
    }
}