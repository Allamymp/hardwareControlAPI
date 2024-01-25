package org.portfolio.hardwarecontrollerapi.model.record;

import java.time.LocalDateTime;

public record ErrorResponseRecord(String message, String details, LocalDateTime timeStamp) {
}
