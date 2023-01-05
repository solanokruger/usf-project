package uol.compass.project.usf.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import uol.compass.project.usf.constants.ErrorCode;

@Getter
public class TokenNotCreatedExpection extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public TokenNotCreatedExpection() {
        super(ErrorCode.TOKEN_NOT_CREATED.name());
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorCode = ErrorCode.TOKEN_NOT_CREATED;
        this.details = ErrorCode.TOKEN_NOT_CREATED.getMessage();
    }

}
