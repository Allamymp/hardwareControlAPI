package org.portfolio.hardwarecontrollerapi.repository;

import org.portfolio.hardwarecontrollerapi.model.entities.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardWareRepository extends JpaRepository<Hardware,Long> {
}
