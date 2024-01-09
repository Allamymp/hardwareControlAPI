package org.portfolio.hardwarecontrollerapi.controller;

import org.portfolio.hardwarecontrollerapi.model.DTO.ClientRequestDTO;
import org.portfolio.hardwarecontrollerapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    ClientService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Validated ClientRequestDTO data) {
        return ResponseEntity.ok().body(service.create(data));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Validated ClientRequestDTO data) {
        return ResponseEntity.ok().body(service.update(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Validated long id) {
        return ResponseEntity.ok().body(service.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @Validated long id) {
        return ResponseEntity.ok().body(service.findByID(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
}
