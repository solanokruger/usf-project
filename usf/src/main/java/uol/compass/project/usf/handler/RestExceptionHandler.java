package uol.compass.project.usf.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uol.compass.project.usf.constants.ErrorCode;
import uol.compass.project.usf.model.dto.ExceptionResponse;
import uol.compass.project.usf.model.exceptions.*;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INVALID_PARAMETER, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INVALID_PARAMETER, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<String> errors = new ArrayList<>();
        fieldErrors.forEach(error -> 
            errors.add(String.format("%s : %s", error.getField(), error.getDefaultMessage()))
        );

        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(UsfNotFoundException.class)
    public final ResponseEntity<Object> handleUsfNotFoundException(UsfNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.USF_NOT_FOUND, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public final ResponseEntity<Object> handleTeamNotFoundException(TeamNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.TEAM_NOT_FOUND, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public final ResponseEntity<Object> handleDoctorNotFoundException(DoctorNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.DOCTOR_NOT_FOUND, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.USER_ALREADY_EXISTS, ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.USER_NOT_FOUND, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(SolicitationNotFoundException.class)
    public final ResponseEntity<Object> handleSolicitationNotFoundException(SolicitationNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.SOLICITATION_NOT_FOUND, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.RESOURCE_NOT_FOUND, ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    public final ResponseEntity<Object> handleInventoryNotFoundException(InventoryNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INVENTORY_NOT_FOUND, ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    
    @ExceptionHandler(TokenNotCreatedExpection.class)
    public final ResponseEntity<Object> handleTokenNotCreatedException(TokenNotCreatedExpection ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.TOKEN_NOT_CREATED, ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(TokenNotValid.class)
    public final ResponseEntity<Object> handleTokenNotValidException(TokenNotValid ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.TOKEN_NOT_VALID, ex);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionResponse);
    }

    @ExceptionHandler(PasswordNotValidException.class)
    public final ResponseEntity<Object> handlePasswordNotValidException(PasswordNotValidException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.PASSWORD_NOT_VALID, ex);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INTERNAL_SERVER_ERROR, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public final ResponseEntity<Object> handleIllegalArgumentException(PropertyReferenceException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> objectAldreadyRegistered(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> objectNotRegistered(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
