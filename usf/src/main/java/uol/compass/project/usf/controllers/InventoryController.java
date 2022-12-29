package uol.compass.project.usf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uol.compass.project.usf.dto.request.InventoryRequestDTO;
import uol.compass.project.usf.dto.response.InventoryResponseDTO;
import uol.compass.project.usf.dto.response.InventoryResponseParameters;
import uol.compass.project.usf.services.InventoryServiceImpl;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

    @PreAuthorize("hasRole('USF_OPERATOR')")
    @PostMapping
    public ResponseEntity<InventoryResponseDTO> create(@RequestBody @Valid InventoryRequestDTO request) {
        InventoryResponseDTO response = inventoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USF_OPERATOR')")
    @GetMapping
    public ResponseEntity<InventoryResponseParameters> findAll(Pageable pageable) {
        InventoryResponseParameters response = inventoryService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USF_OPERATOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<InventoryResponseDTO> findById(@PathVariable("id") Long id) {
        InventoryResponseDTO response = inventoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('USF_OPERATOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<InventoryResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody @Valid InventoryRequestDTO request) {
        InventoryResponseDTO response = inventoryService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('USF_OPERATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
