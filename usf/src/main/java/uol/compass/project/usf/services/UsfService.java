package uol.compass.project.usf.services;

import uol.compass.project.usf.dto.request.UsfRequestDTO;
import uol.compass.project.usf.dto.response.UsfResponseDTO;

public interface UsfService {
    
    UsfResponseDTO create(UsfRequestDTO request);
    
}
