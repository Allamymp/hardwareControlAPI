package org.portfolio.hardwarecontrollerapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.portfolio.hardwarecontrollerapi.model.entities.Hardware;
import org.portfolio.hardwarecontrollerapi.model.record.HardwareRequestRecord;
import org.portfolio.hardwarecontrollerapi.service.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HardwareControllerTest {

    @Mock
    private HardwareService hardwareService;

    @InjectMocks
    private HardwareController hardwareController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        // Mocking data
        HardwareRequestRecord data = new HardwareRequestRecord(1L, "Test Hardware", "Test Model", "Test Address", 2L);

        // Mocking service behavior
        doNothing().when(hardwareService).create(data);

        // Performing the test
        ResponseEntity<?> responseEntity = hardwareController.create(data);

        // Assertions
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(hardwareService, times(1)).create(data);
    }

    @Test
    void update() {
        // Mocking data
        HardwareRequestRecord data = new HardwareRequestRecord(1L, "Updated Hardware", "Updated Model", "Updated Address", 2L);

        // Mocking service behavior
        doNothing().when(hardwareService).update(data);

        // Performing the test
        ResponseEntity<?> responseEntity = hardwareController.update(data);

        // Assertions
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(hardwareService, times(1)).update(data);
    }

    @Test
    void delete() {
        long hardwareId = 1L;

        // Mocking service behavior
        doNothing().when(hardwareService).delete(hardwareId);

        // Performing the test
        ResponseEntity<?> responseEntity = hardwareController.delete(hardwareId);

        // Assertions
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(hardwareService, times(1)).delete(hardwareId);
    }

    @Test
    void findById() {
        long hardwareId = 1L;
        Hardware hardware = new Hardware("Test Hardware", "Test Model", "Test Address", null);

        // Mocking service behavior
        when(hardwareService.findByID(hardwareId)).thenReturn(hardware);

        // Performing the test
        ResponseEntity<?> responseEntity = hardwareController.findById(hardwareId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(hardware, responseEntity.getBody());
        verify(hardwareService, times(1)).findByID(hardwareId);
    }

    @Test
    void findAll() {
        // Mocking data
        List<Hardware> hardwareList = Arrays.asList(
                new Hardware("Hardware 1", "Model 1", "Address 1", null),
                new Hardware("Hardware 2", "Model 2", "Address 2", null)
        );

        // Mocking service behavior
        when(hardwareService.findAll()).thenReturn(hardwareList);

        // Performing the test
        ResponseEntity<?> responseEntity = hardwareController.findAll();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(hardwareList, responseEntity.getBody());
        verify(hardwareService, times(1)).findAll();
    }
}
