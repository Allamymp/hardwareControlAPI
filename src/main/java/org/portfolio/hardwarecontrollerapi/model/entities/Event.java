package org.portfolio.hardwarecontrollerapi.model.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="hardware_id")
    private Hardware hardware;
    private String message;
    private LocalDate date;
    private boolean executed;
    private String prefix;
    private String endpoint;

    public Event(Hardware hardware, String message, String prefix, String endpoint) {
        this.hardware = hardware;
        this.message = message;
        this.date = LocalDate.now();
        this.executed = false;
        this.prefix = prefix;
        this.endpoint = endpoint;
    }
}
