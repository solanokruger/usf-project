package uol.compass.project.usf.model.entities;

import lombok.*;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "team")
@Table(name = "team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color")
    private String color;

    @OneToOne
    @JoinColumn(name = "current_usf_id")
    @JsonIgnore
    private UsfEntity currentUSF;

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<DoctorEntity> doctors;

}
