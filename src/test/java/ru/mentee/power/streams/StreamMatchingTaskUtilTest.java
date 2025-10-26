package ru.mentee.power.streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

public class StreamMatchingTaskUtilTest {

    private List<User> testUsers;
    private List<User> emptyUserList;

    @BeforeEach
    void setUp() {
        testUsers = List.of(
                new User("alice", "alice@example.com", true, 55),
                new User("bob", "bob@example.com", false, 20),
                new User("charlie", "charlie@sample.net", true, 105),
                new User("david", "david@spam.com", true, 0),
                new User("eve", "eve@example.com", true, 99)
        );

        emptyUserList = List.of();
    }

    @Test
    @DisplayName("Задача 1: Поиск первого активного пользователя")
    void findFirstActiveUser_ShouldReturnFirstActiveUser() {
        Optional<User> result = StreamMatchingTaskUtil.findFirstActiveUser(testUsers);

        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo("alice");
    }

    @Test
    @DisplayName("Задача 1: Пустой список должен вернуть пустой Optional")
    void findFirstActiveUser_EmptyList_ShouldReturnEmptyOptional() {
        Optional<User> result = StreamMatchingTaskUtil.findFirstActiveUser(emptyUserList);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Задача 1: Список без активных пользователей должен вернуть пустой Optional")
    void findFirstActiveUser_NoActiveUsers_ShouldReturnEmptyOptional() {
        List<User> inactiveUsers = List.of(
                new User("inactive1", "i1@example.com", false, 10),
                new User("inactive2", "i2@example.com", false, 20)
        );

        Optional<User> result = StreamMatchingTaskUtil.findFirstActiveUser(inactiveUsers);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Задача 2: Проверка наличия пользователя с высоким loginCount")
    void hasUserWithHighLoginCount_ShouldMatchCorrectly() {
        boolean result = StreamMatchingTaskUtil.hasUserWithHighLoginCount(testUsers, 100);

        assertThat(result).isTrue(); // charlie имеет loginCount = 105

        boolean noHighLoginCounts = StreamMatchingTaskUtil.hasUserWithHighLoginCount(testUsers, 150);

        assertThat(noHighLoginCounts).isFalse(); // нет пользователя с loginCount > 150
    }

    @Test
    @DisplayName("Задача 2: Пустой список должен вернуть false")
    void hasUserWithHighLoginCount_EmptyList_ShouldReturnFalse() {
        boolean result = StreamMatchingTaskUtil.hasUserWithHighLoginCount(emptyUserList, 100);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Задача 3: Проверка, что все активные имеют loginCount > 0")
    void checkActiveUsersHaveLogins_ShouldMatchCorrectly() {
        boolean result = StreamMatchingTaskUtil.checkActiveUsersHaveLogins(testUsers);

        assertThat(result).isFalse(); // david активный, но loginCount = 0

        List<User> usersWithLogins = List.of(
                new User("alice", "alice@example.com", true, 55),
                new User("bob", "bob@example.com", false, 0), // неактивный может иметь 0
                new User("charlie", "charlie@sample.net", true, 105)
        );

        boolean allActiveHaveLogins = StreamMatchingTaskUtil.checkActiveUsersHaveLogins(usersWithLogins);

        assertThat(allActiveHaveLogins).isTrue(); // все активные имеют loginCount > 0
    }

    @Test
    @DisplayName("Задача 3: Пустой список должен вернуть true (true vacuously)")
    void checkActiveUsersHaveLogins_EmptyList_ShouldReturnTrue() {
        boolean result = StreamMatchingTaskUtil.checkActiveUsersHaveLogins(emptyUserList);

        assertThat(result).isTrue(); // allMatch на пустом потоке возвращает true
    }

    @Test
    @DisplayName("Задача 4: Проверка отсутствия пользователей со спам-доменом")
    void checkNoSpamEmails_ShouldMatchCorrectly() {
        boolean result = StreamMatchingTaskUtil.checkNoSpamEmails(testUsers);

        assertThat(result).isFalse(); // david имеет email david@spam.com

        List<User> usersWithoutSpam = List.of(
                new User("alice", "alice@example.com", true, 55),
                new User("bob", "bob@example.com", false, 20),
                new User("charlie", "charlie@sample.net", true, 105)
        );

        boolean noSpamEmails = StreamMatchingTaskUtil.checkNoSpamEmails(usersWithoutSpam);

        assertThat(noSpamEmails).isTrue(); // нет email, заканчивающихся на @spam.com
    }

    @Test
    @DisplayName("Задача 4: Пустой список должен вернуть true")
    void checkNoSpamEmails_EmptyList_ShouldReturnTrue() {
        boolean result = StreamMatchingTaskUtil.checkNoSpamEmails(emptyUserList);

        assertThat(result).isTrue(); // noneMatch на пустом потоке возвращает true
    }
}
