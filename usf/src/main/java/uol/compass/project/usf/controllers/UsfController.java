package uol.compass.project.usf.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.dto.request.UsfRequestDTO;
import uol.compass.project.usf.dto.response.UsfResponseDTO;
import uol.compass.project.usf.dto.response.UsfResponseParameters;
import uol.compass.project.usf.repositories.UsfRepository;
import uol.compass.project.usf.services.UsfServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usf")
public class UsfController {
    
    private final UsfServiceImpl usfService;

    @PostMapping
    public ResponseEntity<UsfResponseDTO> create(@RequestBody @Valid UsfRequestDTO request) {
        UsfResponseDTO response = usfService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<UsfResponseParameters> findAll(Pageable pageable) {
        UsfResponseParameters response = usfService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
