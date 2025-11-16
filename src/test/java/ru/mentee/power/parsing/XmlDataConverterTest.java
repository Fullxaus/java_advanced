package ru.mentee.power.parsing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class XmlDataConverterTest {
    private final XmlDataConverter converter = new XmlDataConverter();

    @Test
    @Disabled("Реализация XML сериализации/десериализации не требуется в полном объеме или может быть сложной без JAXB")
    @DisplayName("Тест для XML сериализации (если будет реализована)")
    void shouldSerializeConfigurationDataToXml() {
         ConfigurationData data = new ConfigurationData("http://xml.test.com", 8888, List.of("XML_F1"));
         String xml = converter.toXml(data);

         assertThat(xml).contains("<serverUrl>http://xml.test.com</serverUrl>");
    }

    @Test
    @DisplayName("Тест для XML десериализации (если будет реализована)")
    void shouldDeserializeXmlToConfigurationData() {
        String xml = "<configuration><serverUrl>http://from.xml</serverUrl><port>5678</port><featureFlags><flag>XFLAG</flag></featureFlags></configuration>";
        ConfigurationData expectedData = new ConfigurationData("http://from.xml", 5678, List.of("XFLAG"));

        ConfigurationData actualData = converter.fromXml(xml);

         assertThat(actualData).isEqualTo(expectedData);

    }
}
