package ru.mentee.power.parsing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationData {
    private String serverUrl;
    private int port;
    private List<String> featureFlags;
}
