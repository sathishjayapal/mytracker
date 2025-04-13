package me.sathish.garmindatainitializer.exception;

import java.net.URI;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final URI NOT_FOUND_TYPE = URI.create("urn:problem-type:resource-not-found");
    private static final URI INVALID_INPUT_TYPE = URI.create("urn:problem-type:invalid-input");
    private static final URI SERVER_ERROR_TYPE = URI.create("urn:problem-type:server-error");
}
