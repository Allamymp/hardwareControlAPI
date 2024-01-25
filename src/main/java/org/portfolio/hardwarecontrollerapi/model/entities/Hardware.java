package org.portfolio.hardwarecontrollerapi.model.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import  java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String model;
    private String address;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="client_id")
    private Client client;
    @OneToMany(mappedBy = "hardware", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Event> eventList = new ArrayList<>();

    public Hardware(String name, String model, String address, Client client) {
        this.name = name;
        this.model = model;
        this.address = address;
        this.client = client;
    }

    public void addEvent(Event event){
        this.eventList.add(event);
    }
}
