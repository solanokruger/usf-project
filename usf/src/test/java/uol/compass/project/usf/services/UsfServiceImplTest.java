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
import uol.compass.project.usf.dto.request.UsfRequestDTO;
import uol.compass.project.usf.dto.response.UsfResponseDTO;
import uol.compass.project.usf.dto.response.UsfResponseParameters;
import uol.compass.project.usf.entities.UsfEntity;
import uol.compass.project.usf.repositories.UsfRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UsfServiceImplTest {

    public static final Long ID = 1L;

    @InjectMocks
    private UsfServiceImpl usfService;

    @Mock
    private UsfRepository usfRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void shouldCreateUsf_sucess() {
        UsfEntity usf = new UsfEntity();
        UsfResponseDTO response = new UsfResponseDTO();
        UsfRequestDTO request = new UsfRequestDTO();

        Mockito.when(usfRepository.save(any())).thenReturn(usf);

        UsfResponseDTO usfResponseDTO = usfService.create(request);

        assertEquals(response, usfResponseDTO);
        verify(usfRepository).save(any());
    }

    @Test
    void shouldFindAllUsfs_sucess() {
        UsfEntity usf = new UsfEntity();
        Page<UsfEntity> page = new PageImpl<>(List.of(usf));
        UsfResponseParameters expectedUsfResponseParameters = getUsfResponseParameters();

        Mockito.when(usfRepository.findAll((Pageable) any())).thenReturn(page);

        UsfResponseParameters usfResponseParameters = usfService.findAll(any(Pageable.class));

        assertEquals(expectedUsfResponseParameters, usfResponseParameters);
    }

    @Test
    void shouldFindUsfById_sucess() {
        UsfEntity usf = new UsfEntity();
        UsfResponseDTO usfResponseDto = new UsfResponseDTO();

        Mockito.when(usfRepository.findById(any())).thenReturn(Optional.of(usf));

        UsfResponseDTO response = usfService.findById(ID);

        assertEquals(response, usfResponseDto);
    }

    @Test
    void shouldUpdateUsf_sucess() {
        UsfEntity usf = new UsfEntity();
        UsfResponseDTO usfResponseDto = new UsfResponseDTO();
        UsfRequestDTO request = new UsfRequestDTO();

        Mockito.when(usfRepository.findById(any())).thenReturn(Optional.of(usf));
        Mockito.when(usfRepository.save(any())).thenReturn(usf);

        UsfResponseDTO response = usfService.update(ID, request);

        assertEquals(response, usfResponseDto);
    }

    @Test
    void shouldDeleteUsf_sucess() {
        UsfEntity usf = new UsfEntity();

        Mockito.when(usfRepository.findById(any())).thenReturn(Optional.of(usf));

        usfService.delete(ID);

        verify(usfRepository).deleteById(any());
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
