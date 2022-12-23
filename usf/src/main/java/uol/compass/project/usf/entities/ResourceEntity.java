package uol.compass.project.usf.entities;

import lombok.*;
import uol.compass.project.usf.enums.EnumCategoryResource;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Resource")
@Table(name = "resource")
public class ResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumCategoryResource category;

}
