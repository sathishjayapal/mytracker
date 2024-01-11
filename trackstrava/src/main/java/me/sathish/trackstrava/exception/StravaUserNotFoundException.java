package me.sathish.trackstrava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StravaUserNotFoundException extends ResourceNotFoundException {
    public StravaUserNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(
                String.format(
                        "%s not found with the given input data %s : '%s'",
                        resourceName, fieldName, fieldValue));
    }

    public StravaUserNotFoundException(Long fieldValue) {
        super(String.format("%l not found with the given input data: ", fieldValue));
    }
}
