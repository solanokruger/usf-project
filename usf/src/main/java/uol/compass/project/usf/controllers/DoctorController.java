package uol.compass.project.usf.controllers;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.project.usf.dto.request.DoctorRequestDTO;
import uol.compass.project.usf.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.dto.response.DoctorResponseParameters;
import uol.compass.project.usf.services.DoctorServiceImpl;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorServiceImpl doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> create(@RequestBody @Valid DoctorRequestDTO request) {
        DoctorResponseDTO response = doctorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<DoctorResponseParameters> findAll(Pageable pageable) {
        DoctorResponseParameters response = doctorService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorResponseDTO> findById(@PathVariable("id") Long id) {
        DoctorResponseDTO response = doctorService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DoctorResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid DoctorRequestDTO request) {
        DoctorResponseDTO response = doctorService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
