package uol.compass.project.usf.entities;

import lombok.*;

import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Team")
@Table(name = "team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COLOR", nullable = false)
    private String color;

    @OneToMany(mappedBy = "idTeam")
    private List<DoctorEntity> doutores;

}
