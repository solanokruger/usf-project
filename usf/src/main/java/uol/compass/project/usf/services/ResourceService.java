package uol.compass.project.usf.services;

import org.springframework.data.domain.Pageable;
import uol.compass.project.usf.dto.request.ResourceRequestDTO;
import uol.compass.project.usf.dto.response.ResourceResponseDTO;
import uol.compass.project.usf.dto.response.ResourceResponseParameters;

public interface ResourceService {
        ResourceResponseDTO createResource(ResourceRequestDTO requestDTO);
        ResourceResponseParameters getAllResources(Pageable pageable);

}
