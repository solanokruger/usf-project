package uol.compass.project.usf.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uol.compass.project.usf.model.dto.request.InventoryRequestDTO;
import uol.compass.project.usf.model.dto.response.InventoryResponseDTO;
import uol.compass.project.usf.model.dto.response.InventoryResponseParameters;
import uol.compass.project.usf.model.entities.InventoryEntity;
import uol.compass.project.usf.model.entities.ResourceEntity;
import uol.compass.project.usf.model.entities.UsfEntity;
import uol.compass.project.usf.model.enums.EnumCategoryResource;
import uol.compass.project.usf.model.repositories.InventoryRepository;
import uol.compass.project.usf.model.services.InventoryServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceImplTest {

    @InjectMocks
    InventoryServiceImpl inventoryService;

    @Mock
    InventoryRepository inventoryRepository;

    @Spy
    ModelMapper modelMapper;

    private final Long ID = 1l;

    @Test
    public void shouldCreateInventoryTest_success(){
        InventoryEntity inventoryEntity = new InventoryEntity();
        InventoryRequestDTO inventoryRequestDTO = new InventoryRequestDTO();
        InventoryResponseDTO responseDTO = new InventoryResponseDTO();

        Mockito.when(inventoryRepository.save(any())).thenReturn(inventoryEntity);

        InventoryResponseDTO inventoryResponseDTO = inventoryService.create(inventoryRequestDTO);

        assertEquals(inventoryResponseDTO.getId(), responseDTO.getId());

        verify(inventoryRepository).save(any());
    }

    @Test
    public void shouldFindAllInventoryTest_success(){
        InventoryEntity inventory = new InventoryEntity();
        Page<InventoryEntity> page = new PageImpl<>(List.of(inventory));
        InventoryResponseParameters expectedInventoryResponseParameters = getInventoryResponseParameters();

        Mockito.when(inventoryRepository.findAll((Pageable) any())).thenReturn(page);

        InventoryResponseParameters inventoryResponseParameters = inventoryService.findAll(any(Pageable.class));

        assertEquals(expectedInventoryResponseParameters, inventoryResponseParameters);
    }

    @Test
    public void shouldFindInventoryByIdTest_success(){
        InventoryEntity inventory = new InventoryEntity();

        Mockito.when(inventoryRepository.findById(ID)).thenReturn(Optional.of(inventory));

        InventoryResponseDTO response = inventoryService.findById(ID);

        assertNotNull(response);
        assertEquals(response.getAmount(), inventory.getAmount());
    }

    @Test
    public void shouldUpdateInventoryTest_success(){
        ResourceEntity resource = new ResourceEntity(
                ID, "Estetoscopio", "Equipamento médico", EnumCategoryResource.EQUIPAMENTO);
        InventoryEntity inventory = new InventoryEntity(ID, resource, new UsfEntity(), 5);

        InventoryResponseDTO expectedResponse = new InventoryResponseDTO();

        InventoryRequestDTO request = new InventoryRequestDTO();
        request.setAmount(10);
        request.setIdResource(resource.getId());

        Mockito.when(inventoryRepository.findById(any())).thenReturn(Optional.of(inventory));
        Mockito.when(inventoryRepository.save(any())).thenReturn(inventory);

        InventoryResponseDTO inventoryResponseDTO = inventoryService.update(ID, request);

        assertEquals(expectedResponse.getId(), inventoryResponseDTO.getId());
        verify(inventoryRepository, times(1)).save(any());
    }

    @Test
    void shouldDeleteInventory_sucess() {
        InventoryEntity inventory = new InventoryEntity();

        Mockito.when(inventoryRepository.findById(any())).thenReturn(Optional.of(inventory));

        inventoryService.delete(ID);

        verify(inventoryRepository).deleteById(any());
    }

    private InventoryResponseParameters getInventoryResponseParameters() {
        return InventoryResponseParameters.builder()
                .numberOfElements(1)
                .totalElements(1L)
                .totalPages(1)
                .inventory(List.of(new InventoryResponseDTO()))
                .build();
    }

}
