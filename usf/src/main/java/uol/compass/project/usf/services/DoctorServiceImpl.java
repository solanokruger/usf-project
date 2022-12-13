package uol.compass.project.usf.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uol.compass.project.usf.dto.request.DoctorRequestDTO;
import uol.compass.project.usf.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.entities.DoctorEntity;
import uol.compass.project.usf.repositories.DoctorRepository;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final ModelMapper modelMapper;

    @Override
    public DoctorResponseDTO create(DoctorRequestDTO request) {
        DoctorEntity doctorToCreate = modelMapper.map(request, DoctorEntity.class);
        DoctorEntity createdDoctor = doctorRepository.save(doctorToCreate);

        return modelMapper.map(createdDoctor, DoctorResponseDTO.class);
    }
    
}
