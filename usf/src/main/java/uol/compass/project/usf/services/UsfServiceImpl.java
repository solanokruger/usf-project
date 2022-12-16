package uol.compass.project.usf.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.dto.request.UsfRequestDTO;
import uol.compass.project.usf.dto.response.UsfResponseDTO;
import uol.compass.project.usf.dto.response.UsfResponseParameters;
import uol.compass.project.usf.entities.UsfEntity;
import uol.compass.project.usf.exceptions.UsfNotFoundException;
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

    @Override
    public UsfResponseParameters findAll(Pageable pageable) {
        Page<UsfEntity> page = usfRepository.findAll(pageable);

        return createUsfResponseParameters(page);
    }

    @Override
    public UsfResponseDTO findById(Long id) {
        UsfEntity usf = getUsfEntity(id);

        return modelMapper.map(usf, UsfResponseDTO.class);
    }

    @Override
    public UsfResponseDTO update(Long id, UsfRequestDTO request) {
        getUsfEntity(id);

        UsfEntity usfToUpdate = modelMapper.map(request, UsfEntity.class);
        usfToUpdate.setId(id);
        UsfEntity updatedUsf = usfRepository.save(usfToUpdate);

        return modelMapper.map(updatedUsf, UsfResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        getUsfEntity(id);
        usfRepository.deleteById(id);
    }

    private UsfEntity getUsfEntity(Long id) {
        return usfRepository.findById(id)
                .orElseThrow(UsfNotFoundException::new);
    }

    private UsfResponseParameters createUsfResponseParameters(Page<UsfEntity> page) {
        List<UsfResponseDTO> usf = page.stream()
                .map(this::createUsfResponseDTO)
                .collect(Collectors.toList());

        return UsfResponseParameters.builder()
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .usf(usf)
                .build();
    }

    private UsfResponseDTO createUsfResponseDTO(UsfEntity usf) {
        return modelMapper.map(usf, UsfResponseDTO.class);
    }

}
