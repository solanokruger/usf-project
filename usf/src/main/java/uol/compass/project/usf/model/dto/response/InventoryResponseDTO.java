package uol.compass.project.usf.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.project.usf.model.entities.ResourceEntity;
import uol.compass.project.usf.model.entities.UsfEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDTO {

    private ResourceEntity resource;
    private UsfEntity usf;
    private int amount;

}
