package ru.saiev.testtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.saiev.testtask.dto.MessageA;
import ru.saiev.testtask.dto.MessageResponse;
import ru.saiev.testtask.service.AdapterService;

/**
 * Контроллер для обработки входящих сообщений и их передачи в другие сервисы
 */
@RestController
@RequiredArgsConstructor
public class AdapterController {

    private final AdapterService adapterService;

    @PostMapping("/processMsg")
    public ResponseEntity<MessageResponse> processMessage(@RequestBody MessageA messageA) {
        return adapterService.processMessage(messageA);
    }

}
