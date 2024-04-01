package ru.saiev.testtask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс, предоставляющий координаты широты и долготы
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coordinates {
    private String latitude;
    private String longitude;
}
