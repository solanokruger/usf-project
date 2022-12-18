package uol.compass.project.usf.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String specialization;
    private long idTeam;
    private long id;
}
