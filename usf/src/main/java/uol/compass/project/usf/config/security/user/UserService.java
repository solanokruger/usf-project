package uol.compass.project.usf.config.security.user;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDTO create(UserRequestDTO request);

    UserResponseDTO update(Long id, UserRequestDTO request);

}
