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
import uol.compass.project.usf.dto.request.ResourceRequestDTO;
import uol.compass.project.usf.dto.response.ResourceResponseDTO;
import uol.compass.project.usf.dto.response.ResourceResponseParameters;
import uol.compass.project.usf.entities.ResourceEntity;
import uol.compass.project.usf.repositories.ResourceRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ResourceServiceImplTest {

    @InjectMocks
    ResourceServiceImpl resourceService;
    @Mock
    ResourceRepository resourceRepository;
    @Spy
    ModelMapper modelMapper;

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
        ResourceResponseDTO response = new ResourceResponseDTO();
        Page<ResourceEntity> page = new PageImpl<>(List.of(resource));
        ResourceResponseParameters expectedResourceResponseParameters = getResourceResponseParameters();

        Mockito.when(resourceService.getAllResources(any(), (Pageable) any())).thenReturn((ResourceResponseParameters) page);

        ResourceResponseParameters resourceResponseParameters = resourceService.getAllResources(null, any(Pageable.class));

        assertEquals(expectedResourceResponseParameters, resourceResponseParameters);
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
