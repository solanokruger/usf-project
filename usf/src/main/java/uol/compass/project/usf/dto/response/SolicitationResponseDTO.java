package uol.compass.project.usf.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.project.usf.entities.UsfEntity;
import uol.compass.project.usf.enums.EnumStatusSolicitation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitationResponseDTO {

    private Long id;

    private Long idResource;

    private LocalDateTime requestedDate;

    private LocalDateTime answeredDate;

    private EnumStatusSolicitation statusSolicitation;

    private UsfEntity idUsf;

    private Long necessaryAmount;

}
