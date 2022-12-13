package uol.compass.project.usf.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.project.usf.entities.AddressEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDTO {
    
    @NotBlank
    private String name;

    @NotBlank
    private String specialization;

    @NotEmpty
    private Long idTeam;

    @NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime initTime;

    @NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime endTime;

    @NotNull
    private AddressEntity address;

}
