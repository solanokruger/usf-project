package uol.compass.project.usf.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.dto.request.CreateUserRoleDTO;
import uol.compass.project.usf.dto.request.UserRequestDTO;
import uol.compass.project.usf.dto.response.UserResponseDTO;
import uol.compass.project.usf.services.RoleUserService;
import uol.compass.project.usf.services.UserServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    private final UserServiceImpl userService;

    private final RoleUserService roleUserService;

    @PostMapping(value = "/create")
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO request) {
        UserResponseDTO response = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid UserRequestDTO request) {
        UserResponseDTO response = userService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/role")
    public ResponseEntity<UserResponseDTO> role(@RequestBody CreateUserRoleDTO createUserRoleDTO) {
        UserResponseDTO response = roleUserService.execute(createUserRoleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
