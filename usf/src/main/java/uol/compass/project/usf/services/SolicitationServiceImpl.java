package uol.compass.project.usf.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.dto.request.SolicitationRequestDTO;
import uol.compass.project.usf.dto.response.SolicitationResponseDTO;
import uol.compass.project.usf.entities.SolicitationEntity;
import uol.compass.project.usf.enums.EnumStatusSolicitation;
import uol.compass.project.usf.repositories.SolicitationRepository;

@Service
@RequiredArgsConstructor
public class SolicitationServiceImpl implements SolicitationService {

    private final SolicitationRepository solicitationRepository;

    private final ModelMapper modelMapper;

    @Override
    public SolicitationResponseDTO create(SolicitationRequestDTO request) {
        SolicitationEntity solicitationToCreate = modelMapper.map(request, SolicitationEntity.class);
        solicitationToCreate.setRequestedDate(LocalDateTime.now());
        solicitationToCreate.setStatusSolicitation(EnumStatusSolicitation.PENDENT);
        SolicitationEntity solicitationCreated = solicitationRepository.save(solicitationToCreate);

        return modelMapper.map(solicitationCreated, SolicitationResponseDTO.class);
    }
    
}
