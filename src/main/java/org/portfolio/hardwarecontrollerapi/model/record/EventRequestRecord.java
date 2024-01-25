package org.portfolio.hardwarecontrollerapi.model.record;

public record EventRequestRecord(long id
        , long hardwareId
        , String message
        , String prefix
        , String endpoint) {
}
