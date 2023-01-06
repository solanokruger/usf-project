package uol.compass.project.usf.services;


import org.springframework.data.domain.Pageable;
import uol.compass.project.usf.model.dto.request.InventoryRequestDTO;
import uol.compass.project.usf.model.dto.response.InventoryResponseDTO;
import uol.compass.project.usf.model.dto.response.InventoryResponseParameters;




public interface InventoryService {
    
    InventoryResponseDTO create(InventoryRequestDTO request);

    InventoryResponseParameters findAll(Pageable pageable);

    InventoryResponseDTO findById(Long id);

    InventoryResponseDTO update(Long id, InventoryRequestDTO request);

    void delete(Long id);

}
