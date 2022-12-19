package uol.compass.project.usf.services;

import uol.compass.project.usf.dto.request.SolicitationRequestDTO;
import uol.compass.project.usf.dto.response.SolicitationResponseDTO;

public interface SolicitationService {
    
    SolicitationResponseDTO create(SolicitationRequestDTO request);

}
