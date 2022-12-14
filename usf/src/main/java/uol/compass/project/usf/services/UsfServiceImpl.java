package uol.compass.project.usf.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.dto.request.UsfRequestDTO;
import uol.compass.project.usf.dto.response.UsfResponseDTO;
import uol.compass.project.usf.entities.UsfEntity;
import uol.compass.project.usf.repositories.UsfRepository;

@Service
@RequiredArgsConstructor
public class UsfServiceImpl implements UsfService {

    private final UsfRepository usfRepository;

    private final ModelMapper modelMapper;

    @Override
    public UsfResponseDTO create(UsfRequestDTO request) {
        UsfEntity usfToCreate = modelMapper.map(request, UsfEntity.class);
        UsfEntity usfCreated = usfRepository.save(usfToCreate);
        
        return modelMapper.map(usfCreated, UsfResponseDTO.class);
    }
    
}
