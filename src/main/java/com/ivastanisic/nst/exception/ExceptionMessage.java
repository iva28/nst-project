package com.ivastanisic.nst.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExceptionMessage {

    private final String message;
    private final HttpStatus httpStatus;

}
