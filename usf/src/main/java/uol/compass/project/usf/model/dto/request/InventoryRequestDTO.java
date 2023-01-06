package uol.compass.project.usf.model.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestDTO {

    @NotNull
    private Long idResource;
    @NotNull
    private Long idUsf;
    @NotNull
    private int amount;

}
