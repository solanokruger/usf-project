package uol.compass.project.usf.services;

import uol.compass.project.usf.dto.request.UsfRequestDto;
import uol.compass.project.usf.dto.response.UsfResponseDTO;

public interface UsfService {
    
    UsfResponseDTO create(UsfRequestDto request);
    
}
