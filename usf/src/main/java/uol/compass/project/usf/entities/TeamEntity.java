package uol.compass.project.usf.entities;

import lombok.*;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "team")
@Table(name = "team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color", nullable = false)
    private String color;

    @OneToOne
    @JoinColumn(name = "current_usf_id")
    private UsfEntity currentUSF;

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<DoctorEntity> doctors;

}
