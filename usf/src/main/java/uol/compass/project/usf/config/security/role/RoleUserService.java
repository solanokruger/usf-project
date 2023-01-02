package uol.compass.project.usf.config.security.role;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.config.security.user.UserResponseDTO;
import uol.compass.project.usf.config.security.user.UserEntity;
import uol.compass.project.usf.exceptions.UserNotFoundException;
import uol.compass.project.usf.config.security.user.UserRepository;

@Service
@RequiredArgsConstructor
public class RoleUserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserResponseDTO execute(CreateUserRoleDTO createUserRoleDTO) {

        Optional<UserEntity> userExists = userRepository.findById(createUserRoleDTO.getIdUser());
        List<Role> roles;

        if (userExists.isEmpty()) {
            throw new UserNotFoundException();
        }

        roles = createUserRoleDTO.getIdRoles().stream().map(role -> {
            return new Role(role);
        }).collect(Collectors.toList());

        UserEntity user = userExists.get();

        user.setRoles(roles);

        userRepository.save(user);

        return modelMapper.map(user, UserResponseDTO.class);

    }

}
