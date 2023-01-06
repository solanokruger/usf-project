package uol.compass.project.usf.model.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.project.usf.model.entities.ResourceEntity;
import uol.compass.project.usf.model.entities.UsfEntity;
import uol.compass.project.usf.model.enums.EnumStatusSolicitation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitationResponseDTO {

    private Long id;

    private ResourceEntity resource;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime requestDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime answerDate;

    private EnumStatusSolicitation statusSolicitation;

    private UsfEntity usf;

    private Long necessaryAmount;

}
