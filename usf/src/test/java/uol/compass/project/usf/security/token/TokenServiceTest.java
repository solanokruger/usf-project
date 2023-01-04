package uol.compass.project.usf.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Assertions;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    @InjectMocks
    TokenService tokenService;

    @Test
    public void shouldGenerateTokenTest(){
        UserEntity user = new UserEntity();
        user.setLogin("teste");

        ReflectionTestUtils.setField(tokenService, "secret", "12345678");

        String secret = (String) ReflectionTestUtils.getField(TokenService.class, "secret");
        Assertions.assertNotNull(secret);
        var algorithm = Algorithm.HMAC256(secret);

        String token = JWT.create()
                .withIssuer("USF API")
                .withSubject(user.getLogin())
                .withExpiresAt(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);

        Mockito.when(JWT.create()
                .withIssuer(anyString())
                .withSubject(anyString())
                .withExpiresAt((Instant) any())
                .sign(algorithm))
                .thenReturn(token);
    }
}
