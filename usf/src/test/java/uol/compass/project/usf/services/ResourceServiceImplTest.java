package uol.compass.project.usf.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import uol.compass.project.usf.dto.request.ResourceRequestDTO;
import uol.compass.project.usf.dto.request.TeamRequestDTO;
import uol.compass.project.usf.dto.response.ResourceResponseDTO;
import uol.compass.project.usf.dto.response.TeamResponseDTO;
import uol.compass.project.usf.entities.ResourceEntity;
import uol.compass.project.usf.entities.TeamEntity;
import uol.compass.project.usf.repositories.ResourceRepository;

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
    public void shouldCreateTeamTest_success(){
        ResourceEntity resourceEntity = new ResourceEntity();
        ResourceRequestDTO resourceRequestDTO = new ResourceRequestDTO();
        ResourceResponseDTO responseDTO = new ResourceResponseDTO();

        Mockito.when(resourceRepository.save(any())).thenReturn(resourceEntity);

        ResourceResponseDTO resourceResponseDTO = resourceService.createResource(resourceRequestDTO);

        assertEquals(resourceResponseDTO.getId(), responseDTO.getId());

        verify(resourceRepository).save(any());
    }
}
