package org.portfolio.hardwarecontrollerapi.service;

import org.portfolio.hardwarecontrollerapi.exception.NotFoundEntityException;
import org.portfolio.hardwarecontrollerapi.model.entities.Client;
import org.portfolio.hardwarecontrollerapi.model.entities.Hardware;
import org.portfolio.hardwarecontrollerapi.model.record.HardwareRequestRecord;
import org.portfolio.hardwarecontrollerapi.repository.ClientRepository;
import org.portfolio.hardwarecontrollerapi.repository.HardWareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HardwareService {


    private final HardWareRepository repository;
    private final ClientRepository clientRepository;

    public HardwareService(HardWareRepository repository, ClientRepository clientRepository) {
        this.repository = repository;
        this.clientRepository = clientRepository;
    }

    public void create(HardwareRequestRecord data) {
        Client client = clientRepository.findById(data.clientId())
                .orElseThrow(() -> new NotFoundEntityException("Client not found for id " + data.clientId()));
        Hardware newHardware = new Hardware(data.name(), data.model(), data.address(), client);
        repository.save(newHardware);
        client.addHardware(newHardware);
        clientRepository.save(client);
    }

    public void update(HardwareRequestRecord data) {
        Hardware hardware = repository.findById(data.id())
                .orElseThrow(() -> new NotFoundEntityException("Hardware not found for id " + data.id()));
        hardware.setAddress(data.address());
        hardware.setName(data.name());
        repository.save(hardware);
    }

    public void delete(long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Hardware not found for id " + id)));
    }

    public Hardware findByID(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundEntityException("Hardware not found for id " + id));
    }

    public List<Hardware> findAll() {
        return repository.findAll();
    }
}
