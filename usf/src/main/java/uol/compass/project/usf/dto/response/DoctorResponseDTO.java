package uol.compass.project.usf.dto.response;
import lombok.Data;

@Data
public class DoctorResponseDTO {

    private long id;
    private String name;
    private String specialization;
    private long idTeam;

}
