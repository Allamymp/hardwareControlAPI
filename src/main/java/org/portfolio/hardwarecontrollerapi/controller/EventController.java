package org.portfolio.hardwarecontrollerapi.controller;


import org.portfolio.hardwarecontrollerapi.exception.NotFoundEntityException;
import org.portfolio.hardwarecontrollerapi.model.entities.Event;
import org.portfolio.hardwarecontrollerapi.model.record.EventRequestRecord;
import org.portfolio.hardwarecontrollerapi.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Validated EventRequestRecord data) {
        try {
            service.create(data);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NotFoundEntityException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error creating event", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable @Validated long id) {
        try {
            Event event = service.findById(id);
            return ResponseEntity.ok().body(event);
        } catch (NotFoundEntityException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error finding event by id", e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok().body(service.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Error finding all events", e);
        }
    }
}
