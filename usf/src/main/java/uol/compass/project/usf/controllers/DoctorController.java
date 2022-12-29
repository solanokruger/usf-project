package uol.compass.project.usf.controllers;

import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> create(@RequestBody @Valid DoctorRequestDTO request) {
        DoctorResponseDTO response = doctorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<DoctorResponseParameters> findAll(Pageable pageable) {
        DoctorResponseParameters response = doctorService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USF_OPERATOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorResponseDTO> findById(@PathVariable("id") Long id) {
        DoctorResponseDTO response = doctorService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<DoctorResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody @Valid DoctorRequestDTO request) {
        DoctorResponseDTO response = doctorService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USF_OPERATOR')")
    @PostMapping(value = "/{idDoctor}/team/{idTeam}")
    public ResponseEntity<DoctorResponseDTO> attachDoctorToTeam(@PathVariable("idDoctor") Long idDoctor,
            @PathVariable("idTeam") Long idTeam) {
        DoctorResponseDTO response = doctorService.attachDoctorToTeam(idDoctor, idTeam);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('USF_OPERATOR')")
    @DeleteMapping(value = "/{idDoctor}/team/{idTeam}")
    public ResponseEntity<DoctorResponseDTO> disattachDoctorFromTeam(@PathVariable("idDoctor") Long idDoctor,
            @PathVariable("idTeam") Long idTeam) {
        DoctorResponseDTO response = doctorService.disattachDoctorFromTeam(idDoctor, idTeam);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
