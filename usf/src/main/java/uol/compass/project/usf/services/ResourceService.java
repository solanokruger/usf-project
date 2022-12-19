package uol.compass.project.usf.services;

import uol.compass.project.usf.dto.request.ResourceRequestDTO;
import uol.compass.project.usf.dto.response.ResourceResponseDTO;
import uol.compass.project.usf.dto.response.UsfResponseDTO;

public interface ResourceService {
        ResourceResponseDTO createResource(ResourceRequestDTO requestDTO);


}
