package org.portfolio.hardwarecontrollerapi.model.DTO;

import java.util.Date;

public record EventRequestDTO(long id, long hardwareId, String message, Date date, String prefixType) {
}
