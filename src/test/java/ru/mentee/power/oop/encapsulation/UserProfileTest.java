package ru.mentee.power.oop.encapsulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для класса UserProfile")
public class UserProfileTest {

    private UserProfile userProfile;
    private final int userId = 1;
    private final String initialUsername = "testuser";
    private final String initialEmail = "test@example.com";

    @BeforeEach
    void setUp() {
        userProfile = new UserProfile(userId, initialUsername, initialEmail);
    }

    @Test
    @DisplayName("Конструктор должен корректно инициализировать поля")
    void constructorShouldInitializeFields() {
        assertThat(userProfile).isNotNull();
        assertThat(userProfile.getUserId()).isEqualTo(userId);
        assertThat(userProfile.getUsername()).isEqualTo(initialUsername);
        assertThat(userProfile.getEmail()).isEqualTo(initialEmail);
        assertThat(userProfile.getRegistrationDate()).isNotNull();
        assertThat(userProfile.getRegistrationDate()).isEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("Геттеры должны возвращать корректные значения")
    void gettersShouldReturnCorrectValues() {
        assertThat(userProfile.getUserId()).isEqualTo(userId);
        assertThat(userProfile.getUsername()).isEqualTo(initialUsername);
        assertThat(userProfile.getEmail()).isEqualTo(initialEmail);
        assertThat(userProfile.getRegistrationDate()).isInstanceOf(LocalDate.class);
    }

    @Test
    @DisplayName("setUsername должен обновлять имя пользователя для валидного ввода")
    void setUsernameShouldUpdateUsernameForValidInput() {
        String newName = "  NewName  ";
        userProfile.setUsername(newName);
        // ожидаем trim()
        assertThat(userProfile.getUsername()).isEqualTo("NewName");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t\n"})
    @DisplayName("setUsername не должен изменять имя при невалидном вводе")
    void setUsernameShouldNotUpdateUsernameForInvalidInput(String invalidUsername) {
        String before = userProfile.getUsername();
        // Ожидаем IllegalArgumentException при невалидном вводе
        assertThatThrownBy(() -> userProfile.setUsername(invalidUsername))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(userProfile.getUsername()).isEqualTo(before);
    }

    @Test
    @DisplayName("setEmail должен обновлять email для валидного ввода")
    void setEmailShouldUpdateEmailForValidInput() {
        String newEmail = "new.email@example.org";
        userProfile.setEmail(newEmail);
        assertThat(userProfile.getEmail()).isEqualTo(newEmail);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"plainaddress", "no-at-symbol", "   "})
    @DisplayName("setEmail не должен изменять email при невалидном вводе")
    void setEmailShouldNotUpdateEmailForInvalidInput(String invalidEmail) {
        String before = userProfile.getEmail();

        try {
            userProfile.setEmail(invalidEmail);
            assertThat(userProfile.getEmail()).isEqualTo(before);
        } catch (IllegalArgumentException ex) {
            assertThat(userProfile.getEmail()).isEqualTo(before);
        }
    }




    @Test
    @DisplayName("toString должен возвращать непустую строку")
    void toStringShouldReturnNonEmptyString() {
        String s = userProfile.toString();
        assertThat(s).isNotBlank();
        assertThat(s).contains(String.valueOf(userProfile.getUserId()));
        assertThat(s).contains(userProfile.getUsername());
        assertThat(s).contains(userProfile.getEmail());
    }
}
