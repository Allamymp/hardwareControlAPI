package org.portfolio.hardwarecontrollerapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.portfolio.hardwarecontrollerapi.exception.NotFoundEntityException;
import org.portfolio.hardwarecontrollerapi.model.entities.Client;
import org.portfolio.hardwarecontrollerapi.model.entities.Hardware;
import org.portfolio.hardwarecontrollerapi.model.record.HardwareRequestRecord;
import org.portfolio.hardwarecontrollerapi.repository.ClientRepository;
import org.portfolio.hardwarecontrollerapi.repository.HardWareRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HardwareServiceTest {


    @Mock
    private HardWareRepository hardwareRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private HardwareService hardwareService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void create() {
        // Mocking data
        HardwareRequestRecord data = new HardwareRequestRecord(0L, "Test Hardware", "Test Model", "Test Address", 1L);

        // Mocking repository behavior
        when(clientRepository.findById(any())).thenReturn(Optional.of(new Client()));
        when(hardwareRepository.save(any())).thenReturn(new Hardware());

        // Performing the test
        hardwareService.create(data);

        // Verification
        verify(clientRepository, times(1)).findById(any());
        verify(clientRepository, times(1)).save(any());
        verify(hardwareRepository, times(1)).save(any());
    }

    @Test
    void update() {
        // Mocking data
        HardwareRequestRecord data = new HardwareRequestRecord(1L, "Updated Hardware", "Updated Model", "Updated Address", 1L);

        // Mocking repository behavior
        when(hardwareRepository.findById(any())).thenReturn(Optional.of(new Hardware()));

        // Performing the test
        hardwareService.update(data);

        // Verification
        verify(hardwareRepository, times(1)).findById(any());
        verify(hardwareRepository, times(1)).save(any());
    }

    @Test
    void update_NotFound() {
        // Mocking data
        HardwareRequestRecord data = new HardwareRequestRecord(1L, "Updated Hardware", "Updated Model", "Updated Address", 1L);

        // Mocking repository behavior
        when(hardwareRepository.findById(any())).thenReturn(Optional.empty());

        // Performing the test and verifying the exception
        assertThrows(NotFoundEntityException.class, () -> hardwareService.update(data));
    }

    @Test
    void delete() {
        // Mocking data
        long hardwareId = 1L;

        // Mocking repository behavior
        when(hardwareRepository.findById(hardwareId)).thenReturn(Optional.of(new Hardware()));

        // Performing the test
        hardwareService.delete(hardwareId);

        // Verification
        verify(hardwareRepository, times(1)).delete(any());
    }

    @Test
    void delete_NotFound() {
        // Mocking data
        long hardwareId = 1L;

        // Mocking repository behavior
        when(hardwareRepository.findById(hardwareId)).thenReturn(Optional.empty());

        // Performing the test and verifying the exception
        assertThrows(NotFoundEntityException.class, () -> hardwareService.delete(hardwareId));
    }

    @Test
    void findByID() {
        // Mocking data
        long hardwareId = 1L;

        // Mocking repository behavior
        when(hardwareRepository.findById(hardwareId)).thenReturn(Optional.of(new Hardware()));

        // Performing the test
        hardwareService.findByID(hardwareId);

        // Verification
        verify(hardwareRepository, times(1)).findById(hardwareId);
    }

    @Test
    void findByID_NotFound() {
        // Mocking data
        long hardwareId = 1L;

        // Mocking repository behavior
        when(hardwareRepository.findById(hardwareId)).thenReturn(Optional.empty());

        // Performing the test and verifying the exception
        assertThrows(NotFoundEntityException.class, () -> hardwareService.findByID(hardwareId));
    }

    @Test
    void findAll() {
        // Mocking data
        List<Hardware> hardwareList = List.of(new Hardware(), new Hardware());
        when(hardwareRepository.findAll()).thenReturn(hardwareList);

        // Performing the test
        hardwareService.findAll();

        // Verification
        verify(hardwareRepository, times(1)).findAll();
    }
}
