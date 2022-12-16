package uol.compass.project.usf.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsfResponseDTO {

    private Long id;

    private String name;

    private Long idCurrentTeam;

    private String address;

}
