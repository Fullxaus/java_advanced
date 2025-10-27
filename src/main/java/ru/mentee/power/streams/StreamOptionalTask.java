package ru.mentee.power.streams;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamOptionalTask {
    public static void main(String[] args) {
        List<User> userList = List.of(
                new User("alice", "alice@example.com", true, 55),
                new User("bob", "bob@example.com", false, 20),
                new User("charlie", "charlie@sample.net", true, 105),
                new User("david", "david@spam.com", true, 0)
        );

        System.out.println("--- Поиск пользователя Alice ---");
        Optional<User> aliceOpt = StreamOptionalTaskUtil.findUserByUsername(userList, "alice");
        aliceOpt.ifPresent(u -> System.out.println("Найден: " + u));

        System.out.println("--- Поиск пользователя Peter ---");
        Optional<User> peterOpt = StreamOptionalTaskUtil.findUserByUsername(userList, "peter");
        peterOpt.ifPresentOrElse(
                u -> System.out.println("Найден: " + u),
                () -> System.out.println("Peter не найден.")
        );

        System.out.println("--- Получение Email ---");
        String bobEmail = StreamOptionalTaskUtil.getUserEmail(userList, "bob", "not.found@example.com");
        System.out.println("Bob Email: " + bobEmail);
        String peterEmail = StreamOptionalTaskUtil.getUserEmail(userList, "peter", "not.found@example.com");
        System.out.println("Peter Email: " + peterEmail);

        System.out.println("--- Печать Login Count ---");
        StreamOptionalTaskUtil.printActiveLoginCount(userList, "alice"); // Найден и активен
        StreamOptionalTaskUtil.printActiveLoginCount(userList, "bob");   // Найден, но не активен
        StreamOptionalTaskUtil.printActiveLoginCount(userList, "david"); // Найден и активен (0 логинов)
        StreamOptionalTaskUtil.printActiveLoginCount(userList, "peter"); // Не найден

        System.out.println("--- Пользователи с loginCount >= 50 ---");
        List<String> highLoginUsers = StreamOptionalTaskUtil.findUsernamesWithHighLoginCount(userList, 50);
        System.out.println(highLoginUsers); // Ожидается [alice, charlie]
    }
}
