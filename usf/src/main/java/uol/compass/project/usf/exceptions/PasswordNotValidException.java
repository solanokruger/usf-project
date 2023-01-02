package uol.compass.project.usf.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import uol.compass.project.usf.constants.ErrorCode;

@Getter
public class PasswordNotValidException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public PasswordNotValidException() {
        super(ErrorCode.PASSWORD_NOT_VALID.name());
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.errorCode = ErrorCode.PASSWORD_NOT_VALID;
        this.details = ErrorCode.PASSWORD_NOT_VALID.getMessage();
    }

}
