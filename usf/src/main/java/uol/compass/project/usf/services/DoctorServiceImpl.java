package uol.compass.project.usf.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uol.compass.project.usf.model.dto.request.DoctorRequestDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseParameters;
import uol.compass.project.usf.model.entities.DoctorEntity;
import uol.compass.project.usf.model.entities.TeamEntity;
import uol.compass.project.usf.repositories.DoctorRepository;
import uol.compass.project.usf.exceptions.DoctorNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final TeamServiceImpl teamService;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Override
    public DoctorResponseDTO create(DoctorRequestDTO request) {
        DoctorEntity doctorToCreate = modelMapper.map(request, DoctorEntity.class);
        DoctorEntity doctorCreated = doctorRepository.save(doctorToCreate);

        return modelMapper.map(doctorCreated, DoctorResponseDTO.class);
    }

    @Override
    public DoctorResponseParameters findAll(String name, Pageable pageable) {
        Page<DoctorEntity> page = name == null ?
        doctorRepository.findAll(pageable) :
        doctorRepository.findByNameContaining(name, pageable);
        return createDoctorResponseParameters(page);
    }

    @Override
    public DoctorResponseDTO findById(Long id) {
        DoctorEntity doctor = getDoctorEntity(id);

        return modelMapper.map(doctor, DoctorResponseDTO.class);
    }

    @Override
    public DoctorResponseDTO attachDoctorToTeam(Long idDoctor, Long idTeam) {
        TeamEntity team = teamService.findTeamByIdVerication(idTeam);
        DoctorEntity doctor = getDoctorEntity(idDoctor);

        doctor.setTeam(team);

        DoctorEntity doctorAttached = doctorRepository.save(doctor);

        return modelMapper.map(doctorAttached, DoctorResponseDTO.class);
    }

    @Override
    public DoctorResponseDTO disattachDoctorFromTeam(Long idDoctor, Long idTeam) {
        teamService.findTeamByIdVerication(idTeam);
        DoctorEntity doctor = getDoctorEntity(idDoctor);

        doctor.setTeam(null);

        DoctorEntity doctorDisattached = doctorRepository.save(doctor);

        return modelMapper.map(doctorDisattached, DoctorResponseDTO.class);
    }

    @Override
    public DoctorResponseDTO update(Long id, DoctorRequestDTO request) {
        getDoctorEntity(id);

        DoctorEntity doctorToUpdate = modelMapper.map(request, DoctorEntity.class);
        doctorToUpdate.setId(id);
        DoctorEntity updatedDoctor = doctorRepository.save(doctorToUpdate);

        return modelMapper.map(updatedDoctor, DoctorResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        getDoctorEntity(id);
        doctorRepository.deleteById(id);
    }

    private DoctorEntity getDoctorEntity(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(DoctorNotFoundException::new);
    }

    private DoctorResponseParameters createDoctorResponseParameters(Page<DoctorEntity> page) {
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
