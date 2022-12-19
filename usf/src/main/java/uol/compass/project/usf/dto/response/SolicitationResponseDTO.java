package uol.compass.project.usf.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.project.usf.entities.UsfEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitationResponseDTO {

    private Long id;

    private Long idResource;

    private LocalDateTime requestedDate;

    private UsfEntity idUsf;

    private Long necessaryAmount;

}
