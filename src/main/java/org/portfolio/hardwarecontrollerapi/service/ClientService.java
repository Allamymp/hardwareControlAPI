package org.portfolio.hardwarecontrollerapi.service;

import org.portfolio.hardwarecontrollerapi.model.DTO.ClientRequestDTO;
import org.portfolio.hardwarecontrollerapi.model.DTO.ResponseDTO;
import org.portfolio.hardwarecontrollerapi.model.entities.Client;
import org.portfolio.hardwarecontrollerapi.repository.ClientRepository;
import org.portfolio.hardwarecontrollerapi.repository.HardWareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;
    @Autowired
    HardWareRepository hardWareRepository;

    public ResponseDTO create(ClientRequestDTO data) {
        try {
            repository.save(new Client(data.name(), data.login(), data.password(), data.email()));
            return new ResponseDTO("Client " + data.name() + " sucessfuly created!");
        } catch (Exception e) {
            return new ResponseDTO("Error creating client!");
        }
    }
    public ResponseDTO update(ClientRequestDTO data) {
        try {
            Optional<Client> clientToUpdate = repository.findById(data.id());
            if (clientToUpdate.isPresent()) {
                Client client = clientToUpdate.get();
                client.setName(data.name());
                client.setLogin(data.login());
                client.setPassword(data.password());
                repository.save(client);
                return new ResponseDTO("Client " + client.getName() + " updated!");
            } else {
                return new ResponseDTO("Client with id " + data.id() + " not found!");
            }
        } catch (Exception e) {
            return new ResponseDTO("Failed to update client: " + e.getMessage());
        }
    }

    public ResponseDTO delete(long data) {
        try {
            repository.deleteById(data);
            return new ResponseDTO("Client with id " + data + " deleted!");
        } catch (Exception e) {
            return new ResponseDTO("Fait to delete Client with id " + data);
        }
    }
    public Optional<Client> findByID(long data) {
        return repository.findById(data);
    }
    public List<Client> findAll() {
        return repository.findAll();
    }
}
