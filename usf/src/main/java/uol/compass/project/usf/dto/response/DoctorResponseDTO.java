package uol.compass.project.usf.dto.response;

import java.time.LocalDateTime;

import lombok.Data;
import uol.compass.project.usf.model.Address;

@Data
public class DoctorResponseDTO {

    private Long id;
    
    private String name;

    private String specialization;

    private Long idTeam;

    private LocalDateTime initTime;
    
    private LocalDateTime endTime;

    private Address address;

}
