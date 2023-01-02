package uol.compass.project.usf.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.project.usf.model.entities.TeamEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {

    private Long id;

    private String name;

    private String specialization;

    private TeamEntity team;

}
