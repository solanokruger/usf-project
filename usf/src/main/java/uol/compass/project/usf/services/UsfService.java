package uol.compass.project.usf.services;

import org.springframework.data.domain.Pageable;

import uol.compass.project.usf.dto.request.UsfRequestDTO;
import uol.compass.project.usf.dto.response.UsfResponseDTO;
import uol.compass.project.usf.dto.response.UsfResponseParameters;

public interface UsfService {
    
    UsfResponseDTO create(UsfRequestDTO request);

    UsfResponseParameters findAll(Pageable pageable);

    UsfResponseDTO findById(Long id);
    
}