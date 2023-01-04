package uol.compass.project.usf.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;
import uol.compass.project.usf.handler.RestExceptionHandler;
import uol.compass.project.usf.model.exceptions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenHandleUsfNotFoundException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleUsfNotFoundException(new UsfNotFoundException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleTeamNotFoundException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleTeamNotFoundException(new TeamNotFoundException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleDoctorNotFoundException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleDoctorNotFoundException(new DoctorNotFoundException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleSolicitationNotFoundException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleSolicitationNotFoundException(new SolicitationNotFoundException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleUserNotFoundException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleUserNotFoundException(new UserNotFoundException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleUserAlreadyExistsException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleUserAlreadyExistsException(new UserAlreadyExistsException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleResourceNotFoundException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleResourceNotFoundException(new ResourceNotFoundException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleInventoryNotFoundException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleInventoryNotFoundException(new InventoryNotFoundException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleTokenNotFoundException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleTokenNotCreatedException(new TokenNotCreatedExpection());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleTokenNotValidException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleTokenNotValidException(new TokenNotValid());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandlePasswordNotValidException(){
        ResponseEntity<Object> response = exceptionHandler
                .handlePasswordNotValidException(new PasswordNotValidException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleAllExceptions(){
        ResponseEntity<Object> response = exceptionHandler
                .handleAllExceptions(new Exception());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }


}
