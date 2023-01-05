package uol.compass.project.usf.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.model.dto.request.SolicitationRequestDTO;
import uol.compass.project.usf.model.dto.request.SolicitationUpdateRequestDTO;
import uol.compass.project.usf.model.dto.response.SolicitationResponseDTO;
import uol.compass.project.usf.model.dto.response.SolicitationResponseParameters;
import uol.compass.project.usf.model.entities.SolicitationEntity;
import uol.compass.project.usf.model.enums.EnumStatusSolicitation;
import uol.compass.project.usf.repositories.SolicitationRepository;
import uol.compass.project.usf.exceptions.SolicitationNotFoundException;

@Service
@RequiredArgsConstructor
public class SolicitationServiceImpl implements SolicitationService {

    private final SolicitationRepository solicitationRepository;

    private final UsfServiceImpl usfService;

    private final ResourceServiceImpl resourceService;

    private final ModelMapper modelMapper;

    @Override
    public SolicitationResponseDTO create(SolicitationRequestDTO request) {
        SolicitationEntity solicitationToCreate = new SolicitationEntity();
        solicitationToCreate.setResource(resourceService.getResourceByIdVerification(request.getIdResource()));
        solicitationToCreate.setUsf(usfService.getUsfEntity(request.getIdUsf()));
        solicitationToCreate.setNecessaryAmount(request.getNecessaryAmount());
        SolicitationEntity solicitationCreated = solicitationRepository.save(solicitationToCreate);

        return modelMapper.map(solicitationCreated, SolicitationResponseDTO.class);
    }

    @Override
    public SolicitationResponseParameters findAll(String status, Pageable pageable) {
        Page<SolicitationEntity> page = status == null ?
                solicitationRepository.findAll(pageable) :
                solicitationRepository.findAllByStatusSolicitation(status, pageable);
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
            solicitationToUpdate.setStatusSolicitation(EnumStatusSolicitation.CONCLUIDO);
            solicitationToUpdate.setAnswerDate(LocalDateTime.now());
        }

        solicitationToUpdate.setNecessaryAmount(request.getNecessaryAmount());
        SolicitationEntity updatedSolicitation = solicitationRepository.save(solicitationToUpdate);

        return modelMapper.map(updatedSolicitation, SolicitationResponseDTO.class);
    }

    @Override
    public SolicitationResponseDTO delete(Long id) {
        SolicitationEntity solicitationToUpdate = getSolicitationEntity(id);
        solicitationToUpdate.setStatusSolicitation(EnumStatusSolicitation.SUSPENSO);
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
