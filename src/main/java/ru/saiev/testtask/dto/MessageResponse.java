package ru.saiev.testtask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс представляет собой ответ на обработку сообщения.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageResponse {

    private String message;
    private MessageA processedMessage;
}
