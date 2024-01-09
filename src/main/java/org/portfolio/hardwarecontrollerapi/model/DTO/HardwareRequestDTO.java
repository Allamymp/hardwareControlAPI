package org.portfolio.hardwarecontrollerapi.model.DTO;

public record HardwareRequestDTO(long id, String name,String model, String address, long clientId) {
}
