package uol.compass.project.usf.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import uol.compass.project.usf.constants.ErrorCode;

@Getter
public class UserNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.USER_NOT_FOUND;
        this.details = ErrorCode.USER_NOT_FOUND.getMessage();
    }

}
