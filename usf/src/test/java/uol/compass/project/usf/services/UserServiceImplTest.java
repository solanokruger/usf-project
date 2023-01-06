package uol.compass.project.usf.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import uol.compass.project.usf.model.dto.request.UserRequestDTO;
import uol.compass.project.usf.model.dto.request.UserRequestUpdateDTO;
import uol.compass.project.usf.model.dto.response.UserResponseDTO;
import uol.compass.project.usf.model.entities.UserEntity;
import uol.compass.project.usf.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    public static final Long ID = 1L;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private ModelMapper modelMapper;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Test
    public void shouldCreateUserTest_success(){
        UserEntity user = new UserEntity();
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setPassword("12345");
        UserResponseDTO responseDTO = new UserResponseDTO();

        Mockito.when(userRepository.save(any())).thenReturn(user);

        UserResponseDTO userResponseDTO = userService.create(userRequestDTO);

        assertEquals(userResponseDTO.getId(), responseDTO.getId());

        verify(userRepository).save(any());
    }

    @Test
    public void shouldUpdateUserTest_success(){
        UserEntity user = new UserEntity();
        user.setId(ID);
        user.setPassword("$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC");

        UserRequestUpdateDTO request = new UserRequestUpdateDTO();
        request.setOldPassword("123");
        request.setNewPassword("321");

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(any())).thenReturn(user);

        UserResponseDTO response = userService.update(ID, request);

        assertTrue(passwordEncoder().matches("321", response.getPassword()));
    }
}
