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
import uol.compass.project.usf.model.dto.request.ResourceRequestDTO;
import uol.compass.project.usf.model.dto.response.ResourceResponseDTO;
import uol.compass.project.usf.model.dto.response.ResourceResponseParameters;
import uol.compass.project.usf.model.entities.ResourceEntity;
import uol.compass.project.usf.model.enums.EnumCategoryResource;
import uol.compass.project.usf.repositories.ResourceRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ResourceServiceImplTest {

    @InjectMocks
    ResourceServiceImpl resourceService;
    @Mock
    ResourceRepository resourceRepository;
    @Spy
    ModelMapper modelMapper;

    private final Long ID = 1l;

    @Test
    public void shouldCreateResourceTest_success(){
        ResourceEntity resourceEntity = new ResourceEntity();
        ResourceRequestDTO resourceRequestDTO = new ResourceRequestDTO();
        ResourceResponseDTO responseDTO = new ResourceResponseDTO();

        Mockito.when(resourceRepository.save(any())).thenReturn(resourceEntity);

        ResourceResponseDTO resourceResponseDTO = resourceService.createResource(resourceRequestDTO);

        assertEquals(resourceResponseDTO.getId(), responseDTO.getId());

        verify(resourceRepository).save(any());
    }

    @Test
    public void shouldFindAllResourcesTest_success(){
        ResourceEntity resource = new ResourceEntity();
        Page<ResourceEntity> page = new PageImpl<>(List.of(resource));
        ResourceResponseParameters expectedResourceResponseParameters = getResourceResponseParameters();

        Mockito.when(resourceRepository.findAll((Pageable) any())).thenReturn(page);

        ResourceResponseParameters resourceResponseParameters = resourceService.getAllResources(null, any(Pageable.class));

        assertEquals(expectedResourceResponseParameters, resourceResponseParameters);
    }

    @Test
    public void shouldFindResourceByIdTest_success(){
        ResourceEntity resource = new ResourceEntity(
                ID, "Estetoscopio", "Equipamento médico", EnumCategoryResource.EQUIPAMENTO);

        Mockito.when(resourceRepository.findById(ID)).thenReturn(Optional.of(resource));

        ResourceResponseDTO response = resourceService.getResourceById(ID);

        assertNotNull(response);
        assertEquals(response.getId(), resource.getId());
        assertEquals(response.getName(), resource.getName());
    }

    @Test
    public void shouldUpdateResourceTest_success(){
        ResourceEntity resource = new ResourceEntity(
                ID, "Estetoscopio", "Equipamento médico", EnumCategoryResource.EQUIPAMENTO);

        ResourceResponseDTO expectedResponse = new ResourceResponseDTO(
                ID, "Estetoscopio", "Equipamento médico", EnumCategoryResource.EQUIPAMENTO);

        ResourceRequestDTO request = new ResourceRequestDTO();

        request.setCategory(EnumCategoryResource.MEDICAMENTO.toString());
        request.setName("Teste");
        request.setDescription("Equipamento de teste");

        Mockito.when(resourceRepository.findById(any())).thenReturn(Optional.of(resource));
        Mockito.when(resourceRepository.save(any())).thenReturn(resource);

        ResourceResponseDTO resourceResponseDTO = resourceService.update(request, ID);

        assertEquals(expectedResponse.getId(), resourceResponseDTO.getId());
        verify(resourceRepository, times(1)).save(any());
    }

    @Test
    void shouldDeleteResource_sucess() {
        ResourceEntity resource = new ResourceEntity();

        Mockito.when(resourceRepository.findById(any())).thenReturn(Optional.of(resource));

        resourceService.delete(ID);

        verify(resourceRepository).deleteById(any());
    }


    private ResourceResponseParameters getResourceResponseParameters() {
        return ResourceResponseParameters.builder()
                .numberOfElements(1)
                .totalElements(1L)
                .totalPages(1)
                .resources(List.of(new ResourceResponseDTO()))
                .build();
    }
}
