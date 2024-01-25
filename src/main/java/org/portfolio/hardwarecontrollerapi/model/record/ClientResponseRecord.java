package org.portfolio.hardwarecontrollerapi.model.record;

import org.portfolio.hardwarecontrollerapi.model.entities.Hardware;

import java.util.List;

public record ClientResponseRecord(Long id, String name, String login, String email, List<Hardware> hardwareList) {
}
