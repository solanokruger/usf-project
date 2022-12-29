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
import uol.compass.project.usf.repositories.InventoryRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceImplTest {

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ResourceServiceImpl resourceService;

    @Mock
    private UsfServiceImpl usfService;

    @Spy
    private ModelMapper modelMapper;

    private final Long ID = 1l;

    @Test
    public void shouldCreateInventoryTest_success(){
        InventoryEntity inventory = new InventoryEntity();
        InventoryRequestDTO request = new InventoryRequestDTO();
        InventoryResponseDTO response = new InventoryResponseDTO();

        UsfEntity usf = new UsfEntity();

        ResourceEntity resource = new ResourceEntity();

        Mockito.when(resourceService.getResourceByIdVerification(any())).thenReturn(resource);

        Mockito.when(usfService.getUsfEntity(any())).thenReturn(usf);

        Mockito.when(inventoryRepository.save(any())).thenReturn(inventory);

        InventoryResponseDTO inventoryDTO = inventoryService.create(request);

        assertEquals(inventoryDTO, response);
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

        Mockito.when(inventoryRepository.findById(any())).thenReturn(Optional.of(inventory));

        InventoryResponseDTO response = inventoryService.findById(ID);

        assertNotNull(response);
        assertEquals(response.getAmount(), inventory.getAmount());
    }

    @Test
    public void shouldUpdateInventoryTest_success(){
        InventoryEntity inventory = new InventoryEntity();
        InventoryRequestDTO request = new InventoryRequestDTO();
        InventoryResponseDTO response = new InventoryResponseDTO();

        UsfEntity usf = new UsfEntity();

        ResourceEntity resource = new ResourceEntity();

        response.setUsf(usf);
        response.setResource(resource);

        Mockito.when(inventoryRepository.findById(any())).thenReturn(Optional.of(inventory));

        Mockito.when(resourceService.getResourceByIdVerification(any())).thenReturn(resource);

        Mockito.when(usfService.getUsfEntity(any())).thenReturn(usf);

        Mockito.when(inventoryRepository.save(any())).thenReturn(inventory);

        InventoryResponseDTO inventoryDTO = inventoryService.update(ID, request);

        assertEquals(inventoryDTO, response);
        verify(inventoryRepository).save(any());
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
