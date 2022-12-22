package uol.compass.project.usf.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uol.compass.project.usf.dto.request.InventoryRequestDTO;
import uol.compass.project.usf.dto.response.InventoryResponseDTO;
import uol.compass.project.usf.dto.response.InventoryResponseParameters;
import uol.compass.project.usf.entities.InventoryEntity;
import uol.compass.project.usf.exceptions.DoctorNotFoundException;
import uol.compass.project.usf.exceptions.InventoryNotFoundException;
import uol.compass.project.usf.repositories.InventoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public InventoryResponseDTO create(InventoryRequestDTO request) {
        InventoryEntity inventoryToCreate = modelMapper.map(request, InventoryEntity.class);
        InventoryEntity inventoryCreated = inventoryRepository.save(inventoryToCreate);

        return modelMapper.map(inventoryCreated, InventoryResponseDTO.class);
    }


    @Override
    public InventoryResponseParameters findAll(Pageable pageable) {
        Page<InventoryEntity> page = inventoryRepository.findAll(pageable);
        return createInventoryResponseParameters(page);
    }

    @Override
    public InventoryResponseDTO findById(Long id) {
        InventoryEntity inventory = getInventoryEntity(id);

        return modelMapper.map(inventory, InventoryResponseDTO.class);
    }

    @Override
    public InventoryResponseDTO update(Long id, InventoryRequestDTO request) {
        getInventoryEntity(id);

        InventoryEntity inventoryToUpdate = modelMapper.map(request, InventoryEntity.class);
        inventoryToUpdate.setId(id);
        InventoryEntity updatedInventory = inventoryRepository.save(inventoryToUpdate);

        return modelMapper.map(updatedInventory, InventoryResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        getInventoryEntity(id);
        inventoryRepository.deleteById(id);
    }

    private InventoryEntity getInventoryEntity(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(InventoryNotFoundException::new);
    }

    private InventoryResponseParameters createInventoryResponseParameters(Page<InventoryEntity> page) {
        List<InventoryResponseDTO> inventory = page.stream()
                .map(this::createInventoryResponseDto)
                .collect(Collectors.toList());

        return InventoryResponseParameters.builder()
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .inventory(inventory)
                .build();
    }

    private InventoryResponseDTO createInventoryResponseDto(InventoryEntity inventory) {
        return modelMapper.map(inventory, InventoryResponseDTO.class);
    }

}
