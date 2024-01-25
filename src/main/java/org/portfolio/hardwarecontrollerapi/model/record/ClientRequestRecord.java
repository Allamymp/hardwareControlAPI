package org.portfolio.hardwarecontrollerapi.model.record;

public record ClientRequestRecord(long id, String name, String login, String password, String email) {
}
