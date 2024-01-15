package me.sathish.trackgarmin.exception;

public class GarminRunNotFoundException extends ResourceNotFoundException {

    public GarminRunNotFoundException(Long id) {
        super("GarminRun with Id '%d' not found".formatted(id));
    }
}
