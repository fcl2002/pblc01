package wealthwise.backend.infrastructure.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import wealthwise.backend.exceptions.IllegalFieldException;
import wealthwise.backend.exceptions.ObjectAlreadyRegisteredException;
import wealthwise.backend.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    // User's Exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<String> resourceNotFoundHandler(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found: " + exception.getMessage());
    }
    
    @ExceptionHandler(ObjectAlreadyRegisteredException.class)
    private ResponseEntity<String> objectAlreadyRegisteredHandler(ObjectAlreadyRegisteredException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(IllegalFieldException.class)
    private ResponseEntity<String> illegalFieldHandler(IllegalFieldException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Field: " + exception.getField() + " " + exception.getMessage());
    }
}
