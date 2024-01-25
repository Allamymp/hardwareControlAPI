package org.portfolio.hardwarecontrollerapi.model.record;

public record HardwareRequestRecord(long id, String name, String model, String address, long clientId) {
}
