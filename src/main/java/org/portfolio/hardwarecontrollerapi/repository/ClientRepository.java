package org.portfolio.hardwarecontrollerapi.repository;

import org.portfolio.hardwarecontrollerapi.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
