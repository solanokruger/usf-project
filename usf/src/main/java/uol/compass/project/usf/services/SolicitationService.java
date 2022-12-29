package uol.compass.project.usf.services;

import org.springframework.data.domain.Pageable;

import uol.compass.project.usf.model.dto.request.SolicitationRequestDTO;
import uol.compass.project.usf.model.dto.request.SolicitationUpdateRequestDTO;
import uol.compass.project.usf.model.dto.response.SolicitationResponseDTO;
import uol.compass.project.usf.model.dto.response.SolicitationResponseParameters;

public interface SolicitationService {
    
    SolicitationResponseDTO create(SolicitationRequestDTO request);

    SolicitationResponseParameters findAll(Pageable pageable);

    SolicitationResponseDTO findById(Long id);

    SolicitationResponseDTO update(Long id, SolicitationUpdateRequestDTO request);

    void delete(Long id);

}
