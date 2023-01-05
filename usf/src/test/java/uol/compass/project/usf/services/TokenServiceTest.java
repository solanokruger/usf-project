package uol.compass.project.usf.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import uol.compass.project.usf.model.entities.UserEntity;
import uol.compass.project.usf.services.TokenService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    @InjectMocks
    TokenService tokenService;

    String expectedToken;

    String expectedSubjectToken;
    UserEntity user = new UserEntity();

    @BeforeEach
    public void setUp(){
        user.setLogin("teste");

        ReflectionTestUtils.setField(tokenService, "secret", "12345678");

        String secret = "12345678";

        var algorithm = Algorithm.HMAC256(secret);

        expectedToken = JWT.create()
                .withIssuer("USF API")
                .withSubject(user.getLogin())
                .withExpiresAt(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);

        expectedSubjectToken = JWT.require(algorithm)
                .withIssuer("USF API")
                .build()
                .verify(expectedToken)
                .getSubject();
    }

    @Test
    void shouldGenerateTokenTest(){
        String token = tokenService.generateToken(user);

        assertNotNull(token);
        assertEquals(token, expectedToken);
    }

    @Test
    void shouldGetSubject(){
        String token = tokenService.getSubject(expectedToken);

        assertNotNull(token);
        assertEquals(token, expectedSubjectToken);
    }
}
