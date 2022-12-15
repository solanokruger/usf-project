package uol.compass.project.usf.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import uol.compass.project.usf.constants.ErrorCode;

@Getter
public class UsfNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public UsfNotFoundException() {
        super(ErrorCode.USF_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.USF_NOT_FOUND;
        this.details = ErrorCode.USF_NOT_FOUND.getMessage();
    }

}
