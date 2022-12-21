package uol.compass.project.usf.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import uol.compass.project.usf.constants.ErrorCode;

@Getter
public class SolicitationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public SolicitationNotFoundException() {
        super(ErrorCode.SOLICITATION_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.SOLICITATION_NOT_FOUND;
        this.details = ErrorCode.SOLICITATION_NOT_FOUND.getMessage();
    }

}
