package uol.compass.project.usf.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import uol.compass.project.usf.dto.request.ResourceRequestDTO;
import uol.compass.project.usf.dto.response.ResourceResponseDTO;
import uol.compass.project.usf.entities.ResourceEntity;
import uol.compass.project.usf.repositories.ResourceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService{

    private final ModelMapper modelMapper;
    private final ResourceRepository resourceRepository;

    @Override
    public ResourceResponseDTO createResource(ResourceRequestDTO requestDTO) {
        validateResourceName(requestDTO);
        ResourceEntity resourceEntity = modelMapper.map(requestDTO, ResourceEntity.class);
        ResourceEntity createdResource = resourceRepository.save(resourceEntity);
        return modelMapper.map(createdResource, ResourceResponseDTO.class);
    }

    private void validateResourceName(ResourceRequestDTO requestDTO) {
        List<ResourceEntity> all = resourceRepository.findAll();
        List<String> names = new ArrayList();
        for (int i = 0; i < all.size(); i++) {
            names.add(all.get(i).getName());
        }
        if (names.contains(requestDTO.getName())){
            throw new DataIntegrityViolationException("Recurso já registrado");
        }
    }
}
