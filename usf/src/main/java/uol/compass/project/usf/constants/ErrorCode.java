package uol.compass.project.usf.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    
    BAD_REQUEST("Request invalid"),
    INVALID_PARAMETER("Invalid request parameter"),
    INTERNAL_SERVER_ERROR("Internal error has occured"),
    USF_NOT_FOUND("USF not found");

    private final String message;

}
