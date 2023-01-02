package uol.compass.project.usf.services;

import org.springframework.stereotype.Service;

import uol.compass.project.usf.model.dto.request.UserRequestDTO;
import uol.compass.project.usf.model.dto.request.UserRequestUpdateDTO;
import uol.compass.project.usf.model.dto.response.UserResponseDTO;

@Service
public interface UserService {

    UserResponseDTO create(UserRequestDTO request);

    UserResponseDTO update(Long id, UserRequestUpdateDTO request);

}
