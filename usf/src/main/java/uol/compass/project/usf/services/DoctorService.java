package uol.compass.project.usf.services;

import uol.compass.project.usf.dto.request.DoctorRequestDTO;
import uol.compass.project.usf.dto.response.DoctorResponseDTO;

public interface DoctorService {
    
    DoctorResponseDTO create(DoctorRequestDTO request);

}
