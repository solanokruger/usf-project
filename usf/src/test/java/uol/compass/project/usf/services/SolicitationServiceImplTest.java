package uol.compass.project.usf.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.List;

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

import uol.compass.project.usf.dto.request.SolicitationRequestDTO;
import uol.compass.project.usf.dto.response.SolicitationResponseDTO;
import uol.compass.project.usf.dto.response.SolicitationResponseParameters;
import uol.compass.project.usf.entities.SolicitationEntity;
import uol.compass.project.usf.entities.UsfEntity;
import uol.compass.project.usf.repositories.SolicitationRepository;

@ExtendWith(MockitoExtension.class)
public class SolicitationServiceImplTest {

    @InjectMocks
    private SolicitationServiceImpl solicitationService;

    @Mock
    private UsfServiceImpl usfService;

    @Mock
    private SolicitationRepository solicitationRepository;

    @Spy
    private ModelMapper modelMapper;
    
    @Test
    void shouldCreateSolicitation_sucess() {
        SolicitationEntity solicitation = new SolicitationEntity();
        SolicitationRequestDTO request = new SolicitationRequestDTO();
        SolicitationResponseDTO response = new SolicitationResponseDTO();

        UsfEntity usf = new UsfEntity();

        Mockito.when(usfService.getUsfEntity(any())).thenReturn(usf);

        Mockito.when(solicitationRepository.save(any())).thenReturn(solicitation);

        SolicitationResponseDTO responseDTO = solicitationService.create(request);

        assertEquals(response, responseDTO);
        verify(solicitationRepository).save(any());
    }

    @Test
    void shouldFindAllSolicitations_sucess() {
        SolicitationEntity solicitation = new SolicitationEntity();
        Page<SolicitationEntity> page = new PageImpl<>(List.of(solicitation));
        SolicitationResponseParameters expectedSolicitationResponseParameters = getSolicitationResponseParameters();

        Mockito.when(solicitationRepository.findAll((Pageable) any())).thenReturn(page);

        SolicitationResponseParameters solicitationResponseParameters = solicitationService.findAll(any(Pageable.class));

        assertEquals(expectedSolicitationResponseParameters, solicitationResponseParameters);
    }

    private SolicitationResponseParameters getSolicitationResponseParameters() {
        return SolicitationResponseParameters.builder()
                .numberOfElements(1)
                .totalElements(1L)
                .totalPages(1)
                .solicitation(List.of(new SolicitationResponseDTO()))
                .build();
    }

}
