package uol.compass.project.usf.model.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestUpdateDTO {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String name;

    @NotBlank
    private String newPassword;

}
