package org.portfolio.hardwarecontrollerapi.service;

import org.portfolio.hardwarecontrollerapi.client.EventClient;
import org.portfolio.hardwarecontrollerapi.model.DTO.EventRequestDTO;
import org.portfolio.hardwarecontrollerapi.model.DTO.ResponseDTO;
import org.portfolio.hardwarecontrollerapi.model.entities.Event;
import org.portfolio.hardwarecontrollerapi.model.entities.Hardware;
import org.portfolio.hardwarecontrollerapi.repository.EventRepository;
import org.portfolio.hardwarecontrollerapi.repository.HardWareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;
    @Autowired
    private HardWareRepository hardWareRepository;
    @Autowired
    private EventClient client;

    public ResponseDTO create(EventRequestDTO data) {
        Optional<Hardware> hardwareOptional = hardWareRepository.findById(data.hardwareId());
        try {
            if (hardwareOptional.isPresent()) {
                Event event = new Event(hardwareOptional.get(), data.message(),data.prefix(), data.endpoint());
                hardwareOptional.get().addEvent(event);
                hardWareRepository.save(hardwareOptional.get());
                client.sendMessage(event);
                repository.save(event);
                return new ResponseDTO("Event sucessfuly created!");
            } else {
                return new ResponseDTO("Hardware with id " + data.hardwareId() + " not found!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseDTO("Failed to create event!");
        }
    }
    public Optional<Event> findById(long id) {
        return repository.findById(id);
    }

    public List<Event> findAll() {
        return repository.findAll();
    }



}
