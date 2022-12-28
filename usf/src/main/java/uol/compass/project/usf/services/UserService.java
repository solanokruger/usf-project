package uol.compass.project.usf.services;

import uol.compass.project.usf.dto.request.UserRequestDTO;
import uol.compass.project.usf.dto.response.UserResponseDTO;

public interface UserService {

    UserResponseDTO create(UserRequestDTO request);

    UserResponseDTO update(Long id, UserRequestDTO request);

}
