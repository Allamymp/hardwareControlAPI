package org.portfolio.hardwarecontrollerapi.controller;

import org.junit.jupiter.api.Test;
import org.portfolio.hardwarecontrollerapi.model.entities.Client;
import org.portfolio.hardwarecontrollerapi.model.record.ClientRequestRecord;
import org.portfolio.hardwarecontrollerapi.model.record.ClientResponseRecord;
import org.portfolio.hardwarecontrollerapi.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    private final ClientService clientService = mock(ClientService.class);
    private final ClientController clientController = new ClientController(clientService);

    @Test
    void create() {
        // Arrange
        ClientRequestRecord requestData = new ClientRequestRecord(
                1,
                "testName",
                "testLogin",
                "testPassword",
                "test@email.com"
        );
        doNothing().when(clientService).create(requestData);

        // Act
        ResponseEntity<?> response = clientController.create(requestData);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(clientService, times(1)).create(requestData);
    }

    @Test
    void update() {
        // Arrange
        ClientRequestRecord requestData = new ClientRequestRecord(
                1,
                "testName",
                "testLogin",
                "testPassword",
                "test@email.com"
        );
        doNothing().when(clientService).update(requestData);
        // Act
        ResponseEntity<?> response = clientController.update(requestData);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clientService, times(1)).update(requestData);
    }

    @Test
    void delete() {
        // Arrange
        long clientId = 1L;
        doNothing().when(clientService).delete(clientId);

        // Act
        ResponseEntity<?> response = clientController.delete(clientId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clientService, times(1)).delete(clientId);
    }

    @Test
    void findById() {
        // Arrange
        long clientId = 1L;
        ClientResponseRecord expectedResponse = new ClientResponseRecord(
                1L,
                "testName",
                "testLogin",
                "test@email.com",
                null // Assuming hardwareList is not needed for this test
        );
        when(clientService.findByID(clientId)).thenReturn(new Client(
                1L,
                "testName",
                "testLogin",
                "testPassword",
                "test@email.com",
                null // Assuming hardwareList is not needed for this test
        ));

        // Act
        ResponseEntity<?> response = clientController.findById(clientId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(clientService, times(1)).findByID(clientId);
    }

    @Test
    void findAll() {
        // Arrange
        List<ClientResponseRecord> expectedResponse = Arrays.asList(
                new ClientResponseRecord(1L, "testName1", "testLogin1", "test@email.com", null),
                new ClientResponseRecord(2L, "testName2", "testLogin2", "test2@email.com", null)

        );
        when(clientService.findAll()).thenReturn(Arrays.asList(
                new Client(1L, "testName1", "testLogin1", "testPassword1", "test@email.com", null),
                new Client(2L, "testName2", "testLogin2", "testPassword2", "test2@email.com", null)
        ));

        // Act
        ResponseEntity<?> response = clientController.findAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(clientService, times(1)).findAll();
    }
}