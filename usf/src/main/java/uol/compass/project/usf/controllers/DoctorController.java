package uol.compass.project.usf.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.dto.request.DoctorRequestDTO;
import uol.compass.project.usf.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.services.DoctorServiceImpl;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    
    private final DoctorServiceImpl doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> create(@RequestBody @Valid DoctorRequestDTO request) {
        DoctorResponseDTO response = doctorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
