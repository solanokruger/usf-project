package uol.compass.project.usf.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uol.compass.project.usf.entities.DoctorEntity;
import uol.compass.project.usf.entities.UsfEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponseDTO {

    private Long id;

    private String color;

    private UsfEntity currentUSF;

    private List<DoctorEntity> doctors;

}
