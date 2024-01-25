package org.portfolio.hardwarecontrollerapi.controller;

import org.portfolio.hardwarecontrollerapi.exception.NotFoundEntityException;
import org.portfolio.hardwarecontrollerapi.model.entities.Client;
import org.portfolio.hardwarecontrollerapi.model.record.ClientRequestRecord;
import org.portfolio.hardwarecontrollerapi.model.record.ClientResponseRecord;
import org.portfolio.hardwarecontrollerapi.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {


    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Validated ClientRequestRecord data) {
        try {
            service.create(data);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            throw new RuntimeException("error creating client", e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Validated ClientRequestRecord data) {
        try {
            service.update(data);
            return ResponseEntity.noContent().build();
        } catch (NotFoundEntityException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("error updating client", e);
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
            throw new RuntimeException("Error in delete Client", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @Validated long id) {
        try {
            Client client = service.findByID(id);
            return ResponseEntity.ok().body(new ClientResponseRecord(
                    client.getId(),
                    client.getName(),
                    client.getLogin(),
                    client.getEmail(),
                    client.getHardwareList()
            ));

        } catch (NotFoundEntityException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error finding client by id: " + id, e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        try {
            List<ClientResponseRecord> data = new ArrayList<>();
            for (Client client : service.findAll()) {
                data.add((new ClientResponseRecord(
                        client.getId(),
                        client.getName(),
                        client.getLogin(),
                        client.getEmail(),
                        client.getHardwareList()
                )));
            }
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            throw new RuntimeException("Error getting all clients", e);
        }
    }
}
