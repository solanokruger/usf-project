package uol.compass.project.usf.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import uol.compass.project.usf.dto.request.UsfRequestDto;
import uol.compass.project.usf.dto.response.UsfResponseDTO;
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
        UsfRequestDto request = new UsfRequestDto();

        Mockito.when(modelMapper.map(any(), eq(UsfEntity.class))).thenReturn(usf);
        Mockito.when(usfRepository.save(any())).thenReturn(usf);
        Mockito.when(modelMapper.map(any(), eq(UsfResponseDTO.class))).thenReturn(response);

        UsfResponseDTO usfResponseDTO = usfService.create(request);

        assertEquals(response, usfResponseDTO);
        verify(usfRepository).save(any());
    }

}
