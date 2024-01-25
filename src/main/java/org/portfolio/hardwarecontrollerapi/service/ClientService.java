package org.portfolio.hardwarecontrollerapi.service;

import org.portfolio.hardwarecontrollerapi.exception.NotFoundEntityException;
import org.portfolio.hardwarecontrollerapi.model.entities.Client;
import org.portfolio.hardwarecontrollerapi.model.record.ClientRequestRecord;
import org.portfolio.hardwarecontrollerapi.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public void create(ClientRequestRecord data) {
        repository.save(new Client(data.name(), data.login(), data.password(), data.email()));
    }

    public void update(ClientRequestRecord data) {
        Optional<Client> clientToUpdate = Optional.ofNullable(repository.findById(data.id())
                .orElseThrow(() -> new NotFoundEntityException("Client not found for id: " + data.id())));
        if (clientToUpdate.isPresent()) {
            Client client = clientToUpdate.get();
            client.setName(data.name());
            client.setLogin(data.login());
            client.setPassword(data.password());
            repository.save(client);
        }
    }

    public void delete(long data) {
        repository.delete(repository.findById(data).orElseThrow(
                () -> new NotFoundEntityException("Client not found for id: " + data)
        ));
    }

    public Client findByID(long data) {
        return repository.findById(data)
                .orElseThrow(() -> new NotFoundEntityException("Client not found for id " + data));
    }

    public List<Client> findAll() {
        return repository.findAll();
    }
}
