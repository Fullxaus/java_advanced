package ru.mentee.power.streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

public class StreamOptionalTaskTest {

    private List<User> testUsers;
    private final String DEFAULT_EMAIL = "default@mail.com";

    @BeforeEach
    void setUp() {
        testUsers = List.of(
                new User("alice", "alice@example.com", true, 55),
                new User("bob", "bob@example.com", false, 20),
                new User("charlie", "charlie@sample.net", true, 105)
        );
    }

    @Test
    @DisplayName("findUserByUsername - пользователь найден")
    void findUserByUsername_Found() {
        Optional<User> userOpt = StreamOptionalTaskUtil.findUserByUsername(testUsers, "alice");
        assertThat(userOpt).isPresent();
        assertThat(userOpt.get().getUsername()).isEqualTo("alice");
    }

    @Test
    @DisplayName("findUserByUsername - пользователь не найден")
    void findUserByUsername_NotFound() {
        Optional<User> userOpt = StreamOptionalTaskUtil.findUserByUsername(testUsers, "peter");
        assertThat(userOpt).isEmpty();
    }

    @Test
    @DisplayName("findUserByUsername - регистронезависимый поиск")
    void findUserByUsername_CaseInsensitive() {
        Optional<User> userOpt = StreamOptionalTaskUtil.findUserByUsername(testUsers, "ALICE");
        assertThat(userOpt).isPresent();
        assertThat(userOpt.get().getUsername()).isEqualTo("alice");
    }

    @Test
    @DisplayName("getUserEmail - пользователь найден")
    void getUserEmail_Found() {
        String email = StreamOptionalTaskUtil.getUserEmail(testUsers, "alice", DEFAULT_EMAIL);
        assertThat(email).isEqualTo("alice@example.com");
    }

    @Test
    @DisplayName("getUserEmail - пользователь не найден")
    void getUserEmail_NotFound() {
        String email = StreamOptionalTaskUtil.getUserEmail(testUsers, "peter", DEFAULT_EMAIL);
        assertThat(email).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @DisplayName("printActiveLoginCount - активный пользователь")
    void printActiveLoginCount_Active() {
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            StreamOptionalTaskUtil.printActiveLoginCount(testUsers, "alice");
            assertThat(outContent.toString()).contains("Login count for alice: 55");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DisplayName("printActiveLoginCount - неактивный пользователь")
    void printActiveLoginCount_Inactive() {
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            StreamOptionalTaskUtil.printActiveLoginCount(testUsers, "bob");
            assertThat(outContent.toString()).contains("bob найден, но не активен.");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DisplayName("printActiveLoginCount - пользователь не найден")
    void printActiveLoginCount_NotFound() {
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            StreamOptionalTaskUtil.printActiveLoginCount(testUsers, "peter");
            assertThat(outContent.toString()).contains("peter не найден.");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DisplayName("findUsernamesWithHighLoginCount - найдены пользователи")
    void findUsernamesWithHighLoginCount_Found() {
        List<String> usernames = StreamOptionalTaskUtil.findUsernamesWithHighLoginCount(testUsers, 50);
        assertThat(usernames).containsExactlyInAnyOrder("alice", "charlie");
    }

    @Test
    @DisplayName("findUsernamesWithHighLoginCount - не найдены пользователи")
    void findUsernamesWithHighLoginCount_NotFound() {
        List<String> usernames = StreamOptionalTaskUtil.findUsernamesWithHighLoginCount(testUsers, 200);
        assertThat(usernames).isEmpty();
    }
}
