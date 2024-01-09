package org.portfolio.hardwarecontrollerapi.controller;


import org.portfolio.hardwarecontrollerapi.model.DTO.HardwareRequestDTO;
import org.portfolio.hardwarecontrollerapi.service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hardware")
public class HardwareController {
    @Autowired
    private HardwareService service;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Validated HardwareRequestDTO data) {
        return ResponseEntity.ok().body(service.create(data));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Validated HardwareRequestDTO data){
        return ResponseEntity.ok().body(service.update(data));
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable @Validated long id ){
        return ResponseEntity.ok().body(service.delete(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @Validated long id){
        return ResponseEntity.ok().body(service.findByID(id));
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
}
