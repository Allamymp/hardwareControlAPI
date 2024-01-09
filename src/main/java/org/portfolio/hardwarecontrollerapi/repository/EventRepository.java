package org.portfolio.hardwarecontrollerapi.repository;


import org.portfolio.hardwarecontrollerapi.model.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
}
