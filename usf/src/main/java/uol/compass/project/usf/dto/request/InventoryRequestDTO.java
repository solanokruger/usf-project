package uol.compass.project.usf.dto.request;

import javax.validation.constraints.NotNull;

public class InventoryRequestDTO {

    @NotNull
    private int amount;
    private long idUsf;
    private long idResource;
    private long id;

}
