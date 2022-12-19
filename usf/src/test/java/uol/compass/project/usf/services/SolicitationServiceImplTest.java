package uol.compass.project.usf.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import uol.compass.project.usf.dto.request.SolicitationRequestDTO;
import uol.compass.project.usf.dto.response.SolicitationResponseDTO;
import uol.compass.project.usf.entities.SolicitationEntity;
import uol.compass.project.usf.repositories.SolicitationRepository;

@ExtendWith(MockitoExtension.class)
public class SolicitationServiceImplTest {

    @InjectMocks
    private SolicitationServiceImpl solicitationService;

    @Mock
    private SolicitationRepository solicitationRepository;

    @Spy
    private ModelMapper modelMapper;
    
    @Test
    void shouldCreateSolicitation_sucess() {
        SolicitationEntity solicitation = new SolicitationEntity();
        SolicitationRequestDTO request = new SolicitationRequestDTO();
        SolicitationResponseDTO response = new SolicitationResponseDTO();

        Mockito.when(solicitationRepository.save(any())).thenReturn(solicitation);

        SolicitationResponseDTO responseDTO = solicitationService.create(request);

        assertEquals(response, responseDTO);
        verify(solicitationRepository).save(any());
    }

}
