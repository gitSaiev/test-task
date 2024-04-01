package ru.saiev.testtask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Формат сообщений, отправляемых "Service А"
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MessageA {

    private String lng;
    private String msg;

    private Coordinates coordinates;
    private WeatherData weatherData;
}
