package uol.compass.project.usf.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.dto.request.CreateUserRoleDTO;
import uol.compass.project.usf.dto.response.UserResponseDTO;
import uol.compass.project.usf.entities.Role;
import uol.compass.project.usf.entities.UserEntity;
import uol.compass.project.usf.exceptions.UserNotFoundException;
import uol.compass.project.usf.repositories.UserRepository;

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
