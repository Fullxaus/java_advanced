package ru.mentee.power.parsing;

import java.util.Arrays;

public class ParsingDemo {
    public static void main(String[] args) {
        ConfigurationData config = new ConfigurationData(
                "https://example.com/api",
                8080,
                Arrays.asList("FEATURE_A", "FEATURE_B", "FEATURE_X")
        );

        JsonDataConverter jsonConverter = new JsonDataConverter();
        String json = jsonConverter.toJson(config);
        System.out.println("JSON output:");
        System.out.println(json);

        ConfigurationData fromJson = jsonConverter.fromJson(json);
        System.out.println("From JSON object:");
        System.out.println(fromJson);

        XmlDataConverter xmlConverter = new XmlDataConverter();
        String xml = xmlConverter.toXml(config);
        System.out.println("\nXML output:");
        System.out.println(xml);

        ConfigurationData fromXml = xmlConverter.fromXml(xml);
        System.out.println("From XML object:");
        System.out.println(fromXml);
    }
}
