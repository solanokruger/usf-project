package uol.compass.project.usf.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import uol.compass.project.usf.dto.request.UsfRequestDTO;
import uol.compass.project.usf.dto.response.UsfResponseDTO;
import uol.compass.project.usf.dto.response.UsfResponseParameters;
import uol.compass.project.usf.entities.UsfEntity;
import uol.compass.project.usf.repositories.UsfRepository;

@ExtendWith(MockitoExtension.class)
public class UsfServiceImplTest {
    
    @InjectMocks
    private UsfServiceImpl usfService;

    @Mock
    private UsfRepository usfRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void shouldCreateUsf_sucess() {
        UsfEntity usf = new UsfEntity();
        UsfResponseDTO response = new UsfResponseDTO();
        UsfRequestDTO request = new UsfRequestDTO();

        Mockito.when(modelMapper.map(any(), eq(UsfEntity.class))).thenReturn(usf);
        Mockito.when(usfRepository.save(any())).thenReturn(usf);
        Mockito.when(modelMapper.map(any(), eq(UsfResponseDTO.class))).thenReturn(response);

        UsfResponseDTO usfResponseDTO = usfService.create(request);

        assertEquals(response, usfResponseDTO);
        verify(usfRepository).save(any());
    }

    @Test
    void shouldFindAllUsfs_sucess() {
        UsfEntity usf = new UsfEntity();
        UsfResponseDTO response = new UsfResponseDTO();
        Page<UsfEntity> page = new PageImpl<>(List.of(usf));
        UsfResponseParameters expectedUsfResponseParameters = getUsfResponseParameters();

        Mockito.when(usfRepository.findAll((Pageable) any())).thenReturn(page);
        Mockito.when(modelMapper.map(any(), eq(UsfResponseDTO.class))).thenReturn(response);

        UsfResponseParameters usfResponseParameters = usfService.findAll(any(Pageable.class));

        assertEquals(expectedUsfResponseParameters, usfResponseParameters);
    }

    private UsfResponseParameters getUsfResponseParameters() {
        return UsfResponseParameters.builder()
                .numberOfElements(1)
                .totalElements(1L)
                .totalPages(1)
                .usf(List.of(new UsfResponseDTO()))
                .build();
    }

}
