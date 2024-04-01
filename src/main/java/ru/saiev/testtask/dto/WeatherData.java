package ru.saiev.testtask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс для представления данных о погоде.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherData {
    private String condition;
    private int temperature;
}
