package org.portfolio.hardwarecontrollerapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.portfolio.hardwarecontrollerapi.client.EventClient;
import org.portfolio.hardwarecontrollerapi.exception.NotFoundEntityException;
import org.portfolio.hardwarecontrollerapi.model.entities.Event;
import org.portfolio.hardwarecontrollerapi.model.entities.Hardware;
import org.portfolio.hardwarecontrollerapi.model.record.EventRequestRecord;
import org.portfolio.hardwarecontrollerapi.repository.EventRepository;
import org.portfolio.hardwarecontrollerapi.repository.HardWareRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private HardWareRepository hardWareRepository;

    @Mock
    private EventClient eventClient;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        // Mocking data
        EventRequestRecord data = new EventRequestRecord(0L, 1L, "Test Message", "Test Prefix", "Test Endpoint");

        // Mocking repository behavior
        when(hardWareRepository.findById(any())).thenReturn(Optional.of(new Hardware()));
        when(eventRepository.save(any())).thenReturn(new Event());

        // Performing the test
        eventService.create(data);

        // Verification
        verify(hardWareRepository, times(1)).findById(any());
        verify(hardWareRepository, times(1)).save(any());
        verify(eventRepository, times(1)).save(any());
        verify(eventClient, times(1)).sendMessage(any());
    }

    @Test
    void createHardwareNotFound() {
        // Mocking data
        EventRequestRecord data = new EventRequestRecord(0L, 1L, "Test Message", "Test Prefix", "Test Endpoint");

        // Mocking repository behavior
        when(hardWareRepository.findById(any())).thenReturn(Optional.empty());

        // Performing the test
        assertThrows(NotFoundEntityException.class, () -> eventService.create(data));

        // Verification
        verify(hardWareRepository, times(1)).findById(any());
        verify(hardWareRepository, times(0)).save(any());
        verify(eventRepository, times(0)).save(any());
        verify(eventClient, times(0)).sendMessage(any());
    }

    @Test
    void findById() {
        // Mocking data
        long eventId = 1L;
        Event event = new Event();
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        // Performing the test
        eventService.findById(eventId);

        // Verification
        verify(eventRepository, times(1)).findById(eventId);
    }

    @Test
    void findByIdNotFound() {
        // Mocking data
        long eventId = 1L;
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        // Performing the test
        assertThrows(NotFoundEntityException.class, () -> eventService.findById(eventId));

        // Verification
        verify(eventRepository, times(1)).findById(eventId);
    }

    @Test
    void findAll() {
        // Mocking data
        List<Event> eventList = List.of(new Event(), new Event());
        when(eventRepository.findAll()).thenReturn(eventList);

        // Performing the test
        eventService.findAll();

        // Verification
        verify(eventRepository, times(1)).findAll();
    }
}
