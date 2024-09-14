package com.example.ClinicaOdontologicaC3.Exception;

import org.apache.coyote.BadRequestException;

public class CustomBadRequestException extends BadRequestException {

    public CustomBadRequestException(String message) {
        super(message);
    }
}
