package ru.mentee.power.parsing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonDataConverterTest {
    private final JsonDataConverter converter = new JsonDataConverter();

    @Test
    @DisplayName("Должен корректно сериализовать ConfigurationData в JSON")
    void shouldSerializeConfigurationDataToJson() {
        ConfigurationData data = new ConfigurationData("http://test.com", 9090, List.of("F1", "F2"));
        String json = converter.toJson(data);

        assertThat(json).contains("\"serverUrl\":\"http://test.com\"");
        assertThat(json).contains("\"port\":9090");
        assertThat(json).contains("\"featureFlags\":[\"F1\",\"F2\"]");
    }

    @Test
    @DisplayName("Должен корректно десериализовать JSON в ConfigurationData")
    void shouldDeserializeJsonToConfigurationData() {
        String json = "{\"serverUrl\":\"http://example.org\",\"port\":1234,\"featureFlags\":[\"A\",\"B\"]}";
        ConfigurationData expectedData = new ConfigurationData("http://example.org", 1234, List.of("A", "B"));

        ConfigurationData actualData = converter.fromJson(json);

        assertThat(actualData).isEqualTo(expectedData);
    }
}
