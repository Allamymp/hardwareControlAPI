package org.portfolio.hardwarecontrollerapi.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.portfolio.hardwarecontrollerapi.model.entities.Client;
import org.portfolio.hardwarecontrollerapi.model.record.ClientRequestRecord;
import org.portfolio.hardwarecontrollerapi.repository.ClientRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;



    @Test
    void create() {
        // Mocking data
        ClientRequestRecord data = new ClientRequestRecord(0L, "Test Client", "test", "password", "test@example.com");

        // Performing the test
        clientService.create(data);

        // Verification
        verify(clientRepository, times(1)).save(any());
    }

    @Test
    void update() {
        // Mocking data
        long clientId = 1L;
        ClientRequestRecord data = new ClientRequestRecord(clientId, "Updated Client", "updated", "new-password", "updated@example.com");

        // Mocking repository behavior
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(new Client(clientId, "Old Client", "old", "old-password", "old@example.com", null)));

        // Performing the test
        clientService.update(data);

        // Verification
        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).save(any());
    }
    @Test
    void delete() {
        long clientId = 1L;

        // Mocking repository behavior
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(new Client(clientId, "Test Client", "test", "password", "test@example.com", null)));

        // Performing the test
        clientService.delete(clientId);

        // Verification
        verify(clientRepository, times(1)).delete(any());
    }

    @Test
    void findByID() {
        long clientId = 1L;
        Client client = new Client(clientId, "Test Client", "test", "password", "test@example.com", null);

        // Mocking repository behavior
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        // Performing the test
        Client result = clientService.findByID(clientId);

        // Assertions
        assertEquals(client, result);
    }

    @Test
    void findAll() {
        // Mocking data
        List<Client> clientList = Arrays.asList(
                new Client(1L, "Client 1", "client1", "pass1", "client1@example.com", null),
                new Client(2L, "Client 2", "client2", "pass2", "client2@example.com", null)
        );

        // Mocking repository behavior
        when(clientRepository.findAll()).thenReturn(clientList);

        // Performing the test
        List<Client> result = clientService.findAll();

        // Assertions
        assertEquals(clientList, result);
    }
}
