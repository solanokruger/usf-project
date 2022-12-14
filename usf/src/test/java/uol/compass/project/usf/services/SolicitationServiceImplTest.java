package uol.compass.project.usf.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;

import uol.compass.project.usf.model.dto.request.SolicitationRequestDTO;
import uol.compass.project.usf.model.dto.request.SolicitationUpdateRequestDTO;
import uol.compass.project.usf.model.dto.response.SolicitationResponseDTO;
import uol.compass.project.usf.model.dto.response.SolicitationResponseParameters;
import uol.compass.project.usf.model.entities.ResourceEntity;
import uol.compass.project.usf.model.entities.SolicitationEntity;
import uol.compass.project.usf.model.entities.UsfEntity;
import uol.compass.project.usf.repositories.SolicitationRepository;

@ExtendWith(MockitoExtension.class)
public class SolicitationServiceImplTest {

    public static final Long ID = 1L;

    @InjectMocks
    private SolicitationServiceImpl solicitationService;

    @Mock
    private UsfServiceImpl usfService;

    @Mock
    private ResourceServiceImpl resourceService;

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

        ResourceEntity resource = new ResourceEntity();

        Mockito.when(resourceService.getResourceByIdVerification(any())).thenReturn(resource);

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

        SolicitationResponseParameters solicitationResponseParameters =
                solicitationService.findAll(null, any(Pageable.class));

        assertEquals(expectedSolicitationResponseParameters, solicitationResponseParameters);
    }

    @Test
    void shouldFindSolicitationById_sucess() {
        SolicitationEntity solicitation = new SolicitationEntity();
        SolicitationResponseDTO solicitationResponseDto = new SolicitationResponseDTO();

        Mockito.when(solicitationRepository.findById(any())).thenReturn(Optional.of(solicitation));

        SolicitationResponseDTO response = solicitationService.findById(ID);

        assertEquals(response, solicitationResponseDto);
    }

    @Test
    void shouldUpdateSolicitation_sucess() {
        SolicitationEntity solicitation = new SolicitationEntity();
        solicitation.setNecessaryAmount(2L);
        SolicitationResponseDTO solicitationResponseDto = new SolicitationResponseDTO();
        solicitationResponseDto.setNecessaryAmount(1L);
        SolicitationUpdateRequestDTO request = new SolicitationUpdateRequestDTO();
        request.setAmountAdded(1L);

        Mockito.when(solicitationRepository.findById(any())).thenReturn(Optional.of(solicitation));
        Mockito.when(solicitationRepository.save(any())).thenReturn(solicitation);

        SolicitationResponseDTO response = solicitationService.update(ID, request);

        assertEquals(response, solicitationResponseDto);
    }

    @Test
    void shouldDeleteSolicitation_sucess() {
        SolicitationEntity solicitation = new SolicitationEntity();

        Mockito.when(solicitationRepository.findById(any())).thenReturn(Optional.of(solicitation));
        Mockito.when(solicitationRepository.save(any())).thenReturn(solicitation);
        
        solicitationService.delete(ID);
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
