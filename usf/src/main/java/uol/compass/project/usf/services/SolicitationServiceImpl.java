package uol.compass.project.usf.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.dto.request.SolicitationRequestDTO;
import uol.compass.project.usf.dto.response.SolicitationResponseDTO;
import uol.compass.project.usf.entities.SolicitationEntity;
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
    
}
