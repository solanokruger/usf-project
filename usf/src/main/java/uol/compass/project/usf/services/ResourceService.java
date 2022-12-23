package uol.compass.project.usf.services;

import org.springframework.data.domain.Pageable;
import uol.compass.project.usf.dto.request.ResourceRequestDTO;
import uol.compass.project.usf.dto.response.ResourceResponseDTO;
import uol.compass.project.usf.dto.response.ResourceResponseParameters;
import uol.compass.project.usf.enums.EnumCategoryResource;

public interface ResourceService {
        ResourceResponseDTO createResource(ResourceRequestDTO requestDTO);

        ResourceResponseParameters getAllResources(EnumCategoryResource category, Pageable pageable);

        ResourceResponseDTO getResourceById(Long id);

        ResourceResponseDTO update(ResourceRequestDTO request, Long id);

        void delete(Long id);

}
