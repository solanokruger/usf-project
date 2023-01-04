package uol.compass.project.usf.model.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import uol.compass.project.usf.constants.ErrorCode;

@Getter
public class DoctorNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public DoctorNotFoundException() {
        super(ErrorCode.DOCTOR_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.DOCTOR_NOT_FOUND;
        this.details = ErrorCode.DOCTOR_NOT_FOUND.getMessage();
    }

}
