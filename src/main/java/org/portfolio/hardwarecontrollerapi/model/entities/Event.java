package org.portfolio.hardwarecontrollerapi.model.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="hardware_id")
    private Hardware hardware;
    private String message;
    private Date date;
    private boolean executed;
    private String prefixType;

    public Event(Hardware hardware, String message, Date date, String prefixType) {
        this.hardware = hardware;
        this.message = message;
        this.date = date;
        this.executed = false;
        this.prefixType = prefixType;
    }
}
