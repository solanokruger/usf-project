package uol.compass.project.usf.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import uol.compass.project.usf.model.dto.request.UserRequestDTO;
import uol.compass.project.usf.model.dto.response.UserResponseDTO;
import uol.compass.project.usf.model.entities.UserEntity;
import uol.compass.project.usf.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        UserRequestDTO request = new UserRequestDTO();
        request.setPassword("12345");

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(any())).thenReturn(user);

        UserResponseDTO response = userService.update(ID, request);

        Assert.assertEquals(response.getId(), userResponseDTO.getId());
    }
}
