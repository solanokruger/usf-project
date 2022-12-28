package uol.compass.project.usf.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.dto.request.UserRequestDTO;
import uol.compass.project.usf.dto.response.UserResponseDTO;
import uol.compass.project.usf.entities.UserEntity;
import uol.compass.project.usf.exceptions.UserAlreadyExistsException;
import uol.compass.project.usf.exceptions.UserNotFoundException;
import uol.compass.project.usf.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserResponseDTO create(UserRequestDTO request) {
        UserEntity userReceived = modelMapper.map(request, UserEntity.class);

        UserEntity userToBeCreated = userRepository.findByUsername(userReceived.getUsername());

        if (userToBeCreated != null) {
            throw new UserAlreadyExistsException();
        }

        userReceived.setPassword(passwordEncoder().encode(userReceived.getPassword()));

        UserEntity userCreated = userRepository.save(userReceived);
        return modelMapper.map(userCreated, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO request) {
        getUserEntity(id);

        UserEntity userToUpdate = modelMapper.map(request, UserEntity.class);
        userToUpdate.setId(id);
        userToUpdate.setPassword(passwordEncoder().encode(userToUpdate.getPassword()));
        UserEntity updatedUser = userRepository.save(userToUpdate);

        return modelMapper.map(updatedUser, UserResponseDTO.class);
    }

    private UserEntity getUserEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
    
}
