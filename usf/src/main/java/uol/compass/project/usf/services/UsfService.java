package uol.compass.project.usf.services;

import org.springframework.data.domain.Pageable;

import uol.compass.project.usf.model.dto.request.UsfRequestDTO;
import uol.compass.project.usf.model.dto.response.UsfResponseDTO;
import uol.compass.project.usf.model.dto.response.UsfResponseParameters;

public interface UsfService {
    
    UsfResponseDTO create(UsfRequestDTO request);

    UsfResponseParameters findAll(Pageable pageable);

    UsfResponseDTO findById(Long id);

    UsfResponseDTO update(Long id, UsfRequestDTO request);

    void delete(Long id);
    
}
