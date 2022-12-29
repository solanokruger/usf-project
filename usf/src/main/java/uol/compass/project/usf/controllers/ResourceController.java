package uol.compass.project.usf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uol.compass.project.usf.dto.request.ResourceRequestDTO;
import uol.compass.project.usf.dto.response.ResourceResponseDTO;
import uol.compass.project.usf.dto.response.ResourceResponseParameters;
import uol.compass.project.usf.enums.EnumCategoryResource;
import uol.compass.project.usf.services.ResourceServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceServiceImpl resourceService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResourceResponseDTO> create(@RequestBody @Valid ResourceRequestDTO resourceRequestDTO) {
        ResourceResponseDTO responseDTO = resourceService.createResource(resourceRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ResourceResponseParameters> findAll(
            @RequestParam(required = false) EnumCategoryResource category,
            Pageable pageable) {
        ResourceResponseParameters response = resourceService.getAllResources(category, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponseDTO> findById(@PathVariable Long id) {
        ResourceResponseDTO responseDTO = resourceService.getResourceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResourceResponseDTO> update(@PathVariable Long id,
            @RequestBody ResourceRequestDTO request) {
        ResourceResponseDTO responseDTO = resourceService.update(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        resourceService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
