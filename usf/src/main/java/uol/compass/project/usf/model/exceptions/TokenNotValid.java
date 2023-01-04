package uol.compass.project.usf.model.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import uol.compass.project.usf.constants.ErrorCode;

@Getter
public class TokenNotValid extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public TokenNotValid() {
        super(ErrorCode.TOKEN_NOT_VALID.name());
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.errorCode = ErrorCode.TOKEN_NOT_VALID;
        this.details = ErrorCode.TOKEN_NOT_VALID.getMessage();
    }

}
