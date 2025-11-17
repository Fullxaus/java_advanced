package ru.mentee.power.finaltask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    private String ticker;
    private BigDecimal price;
    private LocalDateTime lastUpdate;
}