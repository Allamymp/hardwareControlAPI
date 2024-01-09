package org.portfolio.hardwarecontrollerapi.service;

import org.portfolio.hardwarecontrollerapi.model.DTO.HardwareRequestDTO;
import org.portfolio.hardwarecontrollerapi.model.DTO.ResponseDTO;
import org.portfolio.hardwarecontrollerapi.model.entities.Client;
import org.portfolio.hardwarecontrollerapi.model.entities.Hardware;
import org.portfolio.hardwarecontrollerapi.repository.ClientRepository;
import org.portfolio.hardwarecontrollerapi.repository.HardWareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HardwareService {

    @Autowired
    private HardWareRepository repository;
    @Autowired
    private ClientRepository clientRepository;


    public ResponseDTO create(HardwareRequestDTO data) {
        Optional<Client> client = clientRepository.findById(data.clientId());
        try {
            if (client.isPresent()) {
                Hardware newHardware = new Hardware(data.name(), data.model(), data.address(), client.get());
                repository.save(newHardware);
                client.get().addHardware(newHardware);
                clientRepository.save(client.get());
                return new ResponseDTO("Hardware created for client " + client.get().getName());
            } else {
                return new ResponseDTO("Client with id " + data.clientId() + " not found!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseDTO("Fail in creating hardware!");
        }
    }

    public ResponseDTO update(HardwareRequestDTO data) {
        Optional<Hardware> hardwareOptional = repository.findById(data.id());
        try {
            if (hardwareOptional.isPresent()) {
                hardwareOptional.get().setAddress(data.address());
                hardwareOptional.get().setName(data.name());
                repository.save(hardwareOptional.get());
                return new ResponseDTO("Hardware id " + data.id() + " sucessful updated!");
            } else {
                return new ResponseDTO("Hardware id " + data.id() + " not  found!");
            }
        } catch (Exception e) {
            return new ResponseDTO("Fail in update hardware: " + e.getMessage());
        }

    }

    public ResponseDTO delete(long id) {
        Optional<Hardware> hardware = repository.findById(id);
        try {
            if (hardware.isPresent()) {
                repository.delete(hardware.get());
                return new ResponseDTO("Hardware with id : " + id + " sucessful deleted!");
            } else {
                return new ResponseDTO("Hardware with id : " + id + " not found!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseDTO("Fail to delete Hardware with id: " + id);
        }
    }

    public Optional<Hardware> findByID(long id) {
        return repository.findById(id);
    }

    public List<Hardware> findAll() {
        return repository.findAll();
    }
}
