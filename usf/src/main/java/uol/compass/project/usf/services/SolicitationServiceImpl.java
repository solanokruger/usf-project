package uol.compass.project.usf.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.dto.request.SolicitationRequestDTO;
import uol.compass.project.usf.dto.request.SolicitationUpdateRequestDTO;
import uol.compass.project.usf.dto.response.SolicitationResponseDTO;
import uol.compass.project.usf.dto.response.SolicitationResponseParameters;
import uol.compass.project.usf.entities.SolicitationEntity;
import uol.compass.project.usf.enums.EnumStatusSolicitation;
import uol.compass.project.usf.exceptions.SolicitationNotFoundException;
import uol.compass.project.usf.repositories.SolicitationRepository;

@Service
@RequiredArgsConstructor
public class SolicitationServiceImpl implements SolicitationService {

    private final SolicitationRepository solicitationRepository;

    private final UsfServiceImpl usfService;

    private final ModelMapper modelMapper;

    @Override
    public SolicitationResponseDTO create(SolicitationRequestDTO request) {
        SolicitationEntity solicitationToCreate = new SolicitationEntity();
        solicitationToCreate.setIdResource(request.getIdResource());
        solicitationToCreate.setIdUsf(usfService.getUsfEntity(request.getIdUsf()));
        solicitationToCreate.setNecessaryAmount(request.getNecessaryAmount());
        SolicitationEntity solicitationCreated = solicitationRepository.save(solicitationToCreate);

        return modelMapper.map(solicitationCreated, SolicitationResponseDTO.class);
    }

    @Override
    public SolicitationResponseParameters findAll(Pageable pageable) {
        Page<SolicitationEntity> page = solicitationRepository.findAll(pageable);

        return createSolicitationResponseParameters(page);
    }

    @Override
    public SolicitationResponseDTO findById(Long id) {
        SolicitationEntity solicitation = getSolicitationEntity(id);

        return modelMapper.map(solicitation, SolicitationResponseDTO.class);
    }

    @Override
    public SolicitationResponseDTO update(Long id, SolicitationUpdateRequestDTO request) {
        SolicitationEntity solicitationToUpdate = getSolicitationEntity(id);

        if (request.getNecessaryAmount() == 0) {
            solicitationToUpdate.setStatusSolicitation(EnumStatusSolicitation.CONCLUDED);
            solicitationToUpdate.setAnsweredDate(LocalDateTime.now());
        }

        solicitationToUpdate.setNecessaryAmount(request.getNecessaryAmount());
        SolicitationEntity updatedSolicitation = solicitationRepository.save(solicitationToUpdate);

        return modelMapper.map(updatedSolicitation, SolicitationResponseDTO.class);
    }

    private SolicitationEntity getSolicitationEntity(Long id) {
        return solicitationRepository.findById(id)
                .orElseThrow(SolicitationNotFoundException::new);
    }

    private SolicitationResponseParameters createSolicitationResponseParameters(Page<SolicitationEntity> page) {
        List<SolicitationResponseDTO> solicitation = page.stream()
                .map(this::createSolicitationResponse)
                .collect(Collectors.toList());

        return SolicitationResponseParameters.builder()
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .solicitation(solicitation)
                .build();
    }

    private SolicitationResponseDTO createSolicitationResponse(SolicitationEntity solicitation) {
        return modelMapper.map(solicitation, SolicitationResponseDTO.class);
    }

}
