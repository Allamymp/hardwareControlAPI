package org.portfolio.hardwarecontrollerapi.service;

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

    public ResponseDTO create(EventRequestDTO data) {
        Optional<Hardware> hardwareOptional = hardWareRepository.findById(data.hardwareId());
        try {
            if (hardwareOptional.isPresent()) {
                Event event = new Event(hardwareOptional.get(), data.message(), data.date(),data.prefixType());
                repository.save(event);
                hardwareOptional.get().addEvent(event);
                hardWareRepository.save(hardwareOptional.get());
                return new ResponseDTO("Event sucessfuly created!");
            } else {
                return new ResponseDTO("Hardware with id " + data.hardwareId() + " not found!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseDTO("Failed to create event!");
        }
    }

    public ResponseDTO delete(long id) {
        Optional<Event> eventOptional = repository.findById(id);
        try {
            if (eventOptional.isPresent()) {
                repository.delete(eventOptional.get());
                return new ResponseDTO("Event with id " + id + " suscessufuly deleted!");
            } else {
                return new ResponseDTO("Event id : " + id + " not found!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseDTO("Failed to delete event!");
        }
    }
    public Optional<Event> findById(long id) {
        return repository.findById(id);
    }

    public List<Event> findAll() {
        return repository.findAll();
    }

}
