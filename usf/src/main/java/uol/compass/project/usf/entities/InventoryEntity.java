package uol.compass.project.usf.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "inventory")
@Table(name = "inventory")
public class InventoryEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "resource_id")
    private ResourceEntity resource;

    @OneToOne
    @JoinColumn(name = "usf_id")
    private UsfEntity usf;

    private int amount;

}
