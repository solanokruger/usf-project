package uol.compass.project.usf.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceRequestDTO {

    private String name;

    private String description;

    private String category;

}