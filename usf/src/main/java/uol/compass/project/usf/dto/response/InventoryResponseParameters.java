package uol.compass.project.usf.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.common.annotationfactory.AnnotationDescriptor;

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
