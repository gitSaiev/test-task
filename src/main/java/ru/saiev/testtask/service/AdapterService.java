package ru.saiev.testtask.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.saiev.testtask.dto.*;

import java.util.Map;

/**
 * Сервис для получения данных о погоде и преобразования сообщений
 */
@Service
public class AdapterService {

    @Value("${weather.api.url}")
    private String weatherUrl;

    @Value("${serviceB.url}")
    private String serviceBUrl;

    /**
     * Получает данные о погоде для указанных координат
     * @param coordinates координаты для запроса данных о погоде
     * @return данные о погоде
     */
    public WeatherData getWeather(Coordinates coordinates) {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder uriWeather = UriComponentsBuilder.fromHttpUrl(weatherUrl)
                .queryParam("lat", coordinates.getLatitude())
                .queryParam("lon", coordinates.getLongitude());

        ResponseEntity<Map> response = restTemplate.getForEntity(uriWeather.toUriString(), Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Integer> responseBody = response.getBody();
            if (responseBody != null) {
                String condition = responseBody.get("condition").toString();
                int temperature = responseBody.get("temperature");
                return new WeatherData(condition, temperature);
            } else {
                throw new RuntimeException("This service does not work!");
            }
        }

        return new WeatherData("Unknown", 0);
    }

    /**
     * Обрабатывает входящее сообщение от ServiceA и отправляет его в ServiceB
     * @param messageA - входящее сообщение от ServiceA
     * @return ответ о результате обработки сообщения
     */
    public ResponseEntity<MessageResponse> processMessage(MessageA messageA) {
        if (messageA == null || messageA.getMsg() == null || messageA.getMsg().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Empty message from Service A", null));
        }

        if (!messageA.getMsg().equals("ru")) {
            return ResponseEntity
                    .ok(new MessageResponse("Message language is not Russian, ignoring.", null));
        }

        messageA.setWeatherData(getWeather(messageA.getCoordinates()));
        MessageB messageB = convertToMessageB(messageA);
        ResponseEntity<String> responseEntity = sendToServiceB(messageB);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(new MessageResponse("Message processed successfully", messageA));
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode())
                    .body(new MessageResponse("Error sending message to Service B", null));
        }
    }

    /**
     * Преобразует объект сообщения типа MessageA в MessageB
     * @param messageA объект сообщения типа MessageA
     * @return объект сообщения типа MessageB
     */
    public MessageB convertToMessageB(MessageA messageA) {
        MessageB messageB = new MessageB();
        BeanUtils.copyProperties(messageA, messageB);
        return messageB;
    }

    /**
     * Отправляет сообщение в ServiceB
     * @param messageB - сообщение для отправки в ServiceB
     * @return ответ от ServiceB
     */
    private ResponseEntity<String> sendToServiceB(MessageB messageB) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(serviceBUrl, messageB, String.class);
    }
}
