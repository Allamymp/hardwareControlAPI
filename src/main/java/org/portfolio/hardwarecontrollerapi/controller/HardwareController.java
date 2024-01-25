package org.portfolio.hardwarecontrollerapi.controller;


import org.portfolio.hardwarecontrollerapi.exception.NotFoundEntityException;
import org.portfolio.hardwarecontrollerapi.model.record.HardwareRequestRecord;
import org.portfolio.hardwarecontrollerapi.service.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hardware")
public class HardwareController {
    private final HardwareService service;

    public HardwareController(HardwareService service) {
        this.service = service;
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Validated HardwareRequestRecord data) {
        try {
            service.create(data);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            throw new RuntimeException("Error creating hardware", e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Validated HardwareRequestRecord data) {
        try {
            service.update(data);
            return ResponseEntity.noContent().build();
        } catch (NotFoundEntityException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating hardware", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Validated long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundEntityException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting hardware", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @Validated long id) {
        try {
            return ResponseEntity.ok().body(service.findByID(id));
        } catch (NotFoundEntityException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error finding hardware by id");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        try {
            return ResponseEntity.ok().body(service.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Error finding all hardwares", e);
        }
    }
}
