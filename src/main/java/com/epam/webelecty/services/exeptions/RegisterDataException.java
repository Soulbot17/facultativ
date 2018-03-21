package com.epam.webelecty.services.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="registration data is wrong")
public class RegisterDataException extends RuntimeException {

}
