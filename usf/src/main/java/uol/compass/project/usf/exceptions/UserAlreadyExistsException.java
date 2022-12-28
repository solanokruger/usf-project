package uol.compass.project.usf.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import uol.compass.project.usf.constants.ErrorCode;

@Getter
public class UserAlreadyExistsException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS.name());
        this.httpStatus = HttpStatus.FOUND;
        this.errorCode = ErrorCode.USER_ALREADY_EXISTS;
        this.details = ErrorCode.USER_ALREADY_EXISTS.getMessage();
    }

}
