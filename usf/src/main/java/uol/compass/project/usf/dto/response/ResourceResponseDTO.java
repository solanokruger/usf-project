package uol.compass.project.usf.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uol.compass.project.usf.enums.EnumCategoryResource;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceResponseDTO {

    private Long id;

    private String name;

    private String description;

    private EnumCategoryResource category;

    private int minAmount;

}
