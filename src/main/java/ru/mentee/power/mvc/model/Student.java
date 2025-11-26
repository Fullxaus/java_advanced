package ru.mentee.power.mvc.model;

import lombok.*;
/**
 * Модель студента.
 */
@Data
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private long id;
    private String name;
}

