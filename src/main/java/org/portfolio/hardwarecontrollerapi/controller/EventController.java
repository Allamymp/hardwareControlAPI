package org.portfolio.hardwarecontrollerapi.controller;


import org.portfolio.hardwarecontrollerapi.model.DTO.EventRequestDTO;
import org.portfolio.hardwarecontrollerapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    EventService service;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Validated EventRequestDTO data) {
        return ResponseEntity.ok().body(service.create(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable @Validated long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
}
