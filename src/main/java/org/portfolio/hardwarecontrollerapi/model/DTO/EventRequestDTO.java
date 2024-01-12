package org.portfolio.hardwarecontrollerapi.model.DTO;

import java.time.LocalDate;

public record EventRequestDTO(long id
        , long hardwareId
        , String message
        , String prefix
        , String endpoint) {
}
