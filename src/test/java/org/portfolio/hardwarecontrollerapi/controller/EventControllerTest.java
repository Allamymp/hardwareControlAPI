package org.portfolio.hardwarecontrollerapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.portfolio.hardwarecontrollerapi.model.entities.Event;
import org.portfolio.hardwarecontrollerapi.model.entities.Hardware;
import org.portfolio.hardwarecontrollerapi.model.record.EventRequestRecord;
import org.portfolio.hardwarecontrollerapi.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        // Arrange
        EventRequestRecord requestData = new EventRequestRecord(
                1L,
                1L,
                "testMessage",
                "testPrefix",
                "testEndpoint"
        );
        doNothing().when(eventService).create(requestData);

        // Act
        ResponseEntity<?> response = eventController.create(requestData);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(eventService, times(1)).create(requestData);
    }

    @Test
    void getById() {
        // Arrange
        long eventId = 1L;
        Hardware hardware = new Hardware();
        Event expectedResponse = new Event(
                1L,
                hardware,
                "testMessage",
                LocalDate.now(),
                false,
                "testPrefix",
                "testEndpoint"
        );
        when(eventService.findById(eventId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<?> response = eventController.getById(eventId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(eventService, times(1)).findById(eventId);
    }

    @Test
    void getAll() {
        // Arrange
        List<Event> expectedResponse = Arrays.asList(
                new Event(
                        1L,
                        new Hardware(),
                        "testMessage1",
                        LocalDate.now(),
                        false,
                        "testPrefix1",
                        "testEndpoint1"
                ),
                new Event(
                        2L,
                        new Hardware(),
                        "testMessage2",
                        LocalDate.now(),
                        false,
                        "testPrefix2",
                        "testEndpoint2"
                )
        );
        when(eventService.findAll()).thenReturn(expectedResponse);

        // Act
        ResponseEntity<?> response = eventController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(eventService, times(1)).findAll();
    }
}
