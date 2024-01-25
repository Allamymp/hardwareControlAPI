package org.portfolio.hardwarecontrollerapi.service;

import org.portfolio.hardwarecontrollerapi.client.EventClient;
import org.portfolio.hardwarecontrollerapi.exception.NotFoundEntityException;
import org.portfolio.hardwarecontrollerapi.model.entities.Event;
import org.portfolio.hardwarecontrollerapi.model.entities.Hardware;
import org.portfolio.hardwarecontrollerapi.model.record.EventRequestRecord;
import org.portfolio.hardwarecontrollerapi.repository.EventRepository;
import org.portfolio.hardwarecontrollerapi.repository.HardWareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository repository;
    private final HardWareRepository hardWareRepository;

    private final EventClient client;

    public EventService(EventRepository repository, HardWareRepository hardWareRepository, EventClient client) {
        this.repository = repository;
        this.hardWareRepository = hardWareRepository;
        this.client = client;
    }

    public void create(EventRequestRecord data) {
        Hardware hardware = hardWareRepository.findById(data.hardwareId())
                .orElseThrow(() -> new NotFoundEntityException("Hardware not found for id " + data.hardwareId()));
        Event event = new Event(hardware, data.message(), data.prefix(), data.endpoint());
        hardware.addEvent(event);
        hardWareRepository.save(hardware);
        client.sendMessage(event);
        repository.save(event);
    }

    public Event findById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundEntityException("Event not found with id " + id));
    }

    public List<Event> findAll() {
        return repository.findAll();
    }
}
