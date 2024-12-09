package wealthwise.backend.exceptions;

import lombok.Getter;

@Getter
public class IllegalFieldException extends RuntimeException {
    private String field;

    public IllegalFieldException(String field, String message) {
        super(message);
        this.field = field;
    }
}
