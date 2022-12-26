package uol.compass.project.usf.dto.response;

import lombok.Data;
import uol.compass.project.usf.entities.TeamEntity;

@Data
public class DoctorResponseDTO {

    private Long id;

    private String name;

    private String specialization;

    private TeamEntity team;

}
