package uol.compass.project.usf.dto.request;

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
    private int amount;
    private long idUsf;
    private long idResource;
    private long id;

}
