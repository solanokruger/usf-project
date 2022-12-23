package uol.compass.project.usf.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.project.usf.entities.ResourceEntity;
import uol.compass.project.usf.entities.UsfEntity;
import uol.compass.project.usf.enums.EnumStatusSolicitation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitationResponseDTO {

    private Long id;

    private ResourceEntity idResource;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime requestedDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime answeredDate;

    private EnumStatusSolicitation statusSolicitation;

    private UsfEntity idUsf;

    private Long necessaryAmount;

}
