package uol.compass.project.usf.model.services;

import uol.compass.project.usf.model.dto.request.DoctorRequestDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseParameters;

import org.springframework.data.domain.Pageable;

public interface DoctorService {
    
    DoctorResponseDTO create(DoctorRequestDTO request);

    DoctorResponseParameters findAll(Pageable pageable);

    DoctorResponseDTO findById(Long id);

    DoctorResponseDTO update(Long id, DoctorRequestDTO request);

    void delete(Long id);

    DoctorResponseDTO attachDoctorToTeam(Long idDoctor, Long idTeam);

    DoctorResponseDTO disattachDoctorFromTeam(Long idDoctor, Long idTeam);

}
