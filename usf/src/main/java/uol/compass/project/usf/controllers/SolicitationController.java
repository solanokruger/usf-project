package uol.compass.project.usf.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.dto.request.SolicitationRequestDTO;
import uol.compass.project.usf.dto.request.SolicitationUpdateRequestDTO;
import uol.compass.project.usf.dto.response.SolicitationResponseDTO;
import uol.compass.project.usf.dto.response.SolicitationResponseParameters;
import uol.compass.project.usf.services.SolicitationServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/solicitation")
public class SolicitationController {
    
    private final SolicitationServiceImpl solicitationService;

    @PostMapping
    public ResponseEntity<SolicitationResponseDTO> create(@RequestBody @Valid SolicitationRequestDTO request) {
        SolicitationResponseDTO response = solicitationService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<SolicitationResponseParameters> findAll(Pageable pageable) {
        SolicitationResponseParameters response = solicitationService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SolicitationResponseDTO> findById(@PathVariable("id") Long id) {
        SolicitationResponseDTO response = solicitationService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<SolicitationResponseDTO> update(@PathVariable("id") Long id,
                            @RequestBody @Valid SolicitationUpdateRequestDTO request) {
        SolicitationResponseDTO response = solicitationService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        solicitationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
