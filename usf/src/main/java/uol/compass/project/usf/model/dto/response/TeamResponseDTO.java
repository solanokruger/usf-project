package uol.compass.project.usf.model.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.project.usf.model.entities.DoctorEntity;
import uol.compass.project.usf.model.entities.UsfEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponseDTO {

    private Long id;

    private String color;

    private UsfEntity currentUSF;

    private List<DoctorEntity> doctors;

}
