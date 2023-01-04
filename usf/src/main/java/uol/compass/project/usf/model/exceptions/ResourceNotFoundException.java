package uol.compass.project.usf.model.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import uol.compass.project.usf.constants.ErrorCode;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public ResourceNotFoundException() {
        super(ErrorCode.RESOURCE_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        this.details = ErrorCode.RESOURCE_NOT_FOUND.getMessage();
    }
}
