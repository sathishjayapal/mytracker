package me.sathish.trackstrava.exception;

public class StravaRunNotFoundException extends ResourceNotFoundException {

    public StravaRunNotFoundException(Long id) {
        super("StravaRun with Id '%d' not found".formatted(id));
    }
}
