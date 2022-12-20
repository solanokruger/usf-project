package uol.compass.project.usf.dto.response;

import lombok.*;
import uol.compass.project.usf.enums.EnumCategoryResource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceResponseDTO {

    private Long id;

    private String name;

    private String description;

    private EnumCategoryResource category;

}
