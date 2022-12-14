package uol.compass.project.usf.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsfResponseParameters {

    private Integer numberOfElements;

    private Long totalElements;

    private Integer totalPages;

    private List<UsfResponseDTO> usf;

}
