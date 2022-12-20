package uol.compass.project.usf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.project.usf.dto.request.ResourceRequestDTO;
import uol.compass.project.usf.dto.response.ResourceResponseDTO;
import uol.compass.project.usf.dto.response.ResourceResponseParameters;
import uol.compass.project.usf.services.ResourceServiceImpl;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceServiceImpl resourceService;

    @PostMapping
    public ResponseEntity create(@RequestBody ResourceRequestDTO resourceRequestDTO){
        ResourceResponseDTO responseDTO = resourceService.createResource(resourceRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<ResourceResponseParameters> findAll(Pageable pageable) {
        ResourceResponseParameters response = resourceService.getAllResources(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
