package ru.saiev.testtask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Формат сообщений принимаемых сервисом "B"
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageB {

    private String txt;
    private LocalDateTime createdDt;
    private int currentTemperature;
}
