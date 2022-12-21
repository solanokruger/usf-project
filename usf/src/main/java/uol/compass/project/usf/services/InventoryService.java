package uol.compass.project.usf.services;


import uol.compass.project.usf.dto.request.InventoryRequestDTO;
import uol.compass.project.usf.dto.response.InventoryResponseDTO;
import uol.compass.project.usf.dto.response.InventoryResponseParameters;

import java.awt.print.Pageable;


public interface InventoryService {
    InventoryResponseDTO create(InventoryRequestDTO request);

    InventoryResponseParameters findAll(Pageable pageable);

    InventoryResponseDTO findById(Long id);

    InventoryResponseDTO update(Long id, InventoryRequestDTO request);

    void delete(Long id);

}
