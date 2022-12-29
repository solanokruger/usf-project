package uol.compass.project.usf.config.security.user;

public interface UserService {

    UserResponseDTO create(UserRequestDTO request);

    UserResponseDTO update(Long id, UserRequestDTO request);

}
