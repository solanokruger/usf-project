package uol.compass.project.usf.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import uol.compass.project.usf.constants.ErrorCode;
@Getter

public class InventoryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;


    public InventoryNotFoundException(){
        super (ErrorCode.INVENTORY_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.INVENTORY_NOT_FOUND;
        this.details = ErrorCode.INVENTORY_NOT_FOUND.getMessage();
    }
}
