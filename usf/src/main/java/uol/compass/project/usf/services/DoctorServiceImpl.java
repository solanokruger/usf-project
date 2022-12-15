package uol.compass.project.usf.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uol.compass.project.usf.dto.request.DoctorRequestDTO;
import uol.compass.project.usf.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.dto.response.DoctorResponseParameters;
import uol.compass.project.usf.entities.DoctorEntity;
import uol.compass.project.usf.repositories.DoctorRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Override
    public DoctorResponseDTO create(DoctorRequestDTO request) {
        DoctorEntity doctorToCreate = modelMapper.map(request, DoctorEntity.class);
        DoctorEntity doctorCreated = DoctorRepository.save(doctorToCreate);

        return modelMapper.map(doctorCreated, DoctorResponseDTO.class);
    }

    @Override
    public DoctorResponseParameters findAll(Pageable pageable) {
        Page<DoctorEntity> page = doctorRepository.findAll((org.springframework.data.domain.Pageable) pageable);

        return createDoctorResponseParameters(page);
    }

    public DoctorResponseParameters createDoctorResponseParameters(Page<DoctorEntity> page) {
        List<DoctorResponseDTO> doctor = page.stream()
                .map(this::createDoctorResponseDto)
                .collect(Collectors.toList());

        return DoctorResponseParameters.builder()
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .doctor(doctor)
                .build();
    }

    private DoctorResponseDTO createDoctorResponseDto(DoctorEntity doctor) {
        return modelMapper.map(doctor, DoctorResponseDTO.class);
    }
}
