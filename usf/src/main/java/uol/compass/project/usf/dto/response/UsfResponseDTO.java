package uol.compass.project.usf.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.project.usf.entities.TeamEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsfResponseDTO {

    private Long id;

    private String name;

    private TeamEntity idCurrentTeam;

    private String address;

}
