package org.portfolio.hardwarecontrollerapi.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.portfolio.hardwarecontrollerapi.model.DTO.ResponseDTO;
import org.portfolio.hardwarecontrollerapi.model.entities.Event;
import org.portfolio.hardwarecontrollerapi.model.entities.Hardware;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class EventClient {
    public void sendMessage(Event event) {
        Hardware hardware = event.getHardware();

        if (hardware != null) {
            String hardwareAddress = hardware.getAddress();
            String prefix = event.getPrefix();
            String message = event.getMessage();
            String url;

            if (hardwareAddress != null && !hardwareAddress.isBlank()) {
                // Adiciona o esquema "http" a URL
                url = prefix + "://" + hardwareAddress + "/";
                if (event.getEndpoint()!=null&&!event.getEndpoint().isBlank()) {
                    url += event.getEndpoint();
                }

                // Constroi o corpo da solicitação no formato JSON
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String json = objectMapper.writeValueAsString(new ResponseDTO(message));

                    HttpClient client = HttpClient.newHttpClient();

                    HttpRequest request = HttpRequest.newBuilder()
                            .POST(HttpRequest.BodyPublishers.ofString(json))
                            .uri(URI.create(url))
                            .header("Content-Type", "application/json")
                            .build();

                    client.send(request, HttpResponse.BodyHandlers.ofString());
                    event.setExecuted(true);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                    // Trate exceções específicas aqui
                }
            }
        }
    }
}
