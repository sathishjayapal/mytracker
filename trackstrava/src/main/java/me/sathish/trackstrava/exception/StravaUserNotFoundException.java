package me.sathish.trackstrava.exception;

public class StravaUserNotFoundException extends RuntimeException {
    public StravaUserNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(
            String.format(
                "%s not found with the given input data %s : '%s'",
                resourceName, fieldName, fieldValue));
    }
    public StravaUserNotFoundException(Long fieldValue) {
        super(
            String.format(
                "%l not found with the given input data: ",
                 fieldValue));
    }
}
