package uol.compass.project.usf.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseParameters {

    private Integer numberOfElements;
    private Long totalElements;
    private Integer totalPages;
    private List<InventoryResponseDTO> inventory;

}
