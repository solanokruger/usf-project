package uol.compass.project.usf.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.exceptions.PasswordNotValidException;
import uol.compass.project.usf.exceptions.UserAlreadyExistsException;
import uol.compass.project.usf.exceptions.UserNotFoundException;
import uol.compass.project.usf.model.dto.request.UserRequestDTO;
import uol.compass.project.usf.model.dto.request.UserRequestUpdateDTO;
import uol.compass.project.usf.model.dto.response.UserResponseDTO;
import uol.compass.project.usf.model.entities.UserEntity;
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

        UserEntity userToBeCreated = userRepository.findByLogin(userReceived.getLogin());

        if (userToBeCreated != null) {
            throw new UserAlreadyExistsException();
        }

        userReceived.setPassword(passwordEncoder().encode(userReceived.getPassword()));

        UserEntity userCreated = userRepository.save(userReceived);
        return modelMapper.map(userCreated, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestUpdateDTO request) {
        UserEntity userToUpdate = getUserEntity(id);

        if (!passwordEncoder().matches(request.getOldPassword(), userToUpdate.getPassword())) {
            throw new PasswordNotValidException();
        }

        userToUpdate.setName(request.getName());
        userToUpdate.setPassword(passwordEncoder().encode(request.getNewPassword()));
        UserEntity updatedUser = userRepository.save(userToUpdate);

        return modelMapper.map(updatedUser, UserResponseDTO.class);
    }

    private UserEntity getUserEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
    
}
