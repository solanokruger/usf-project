package uol.compass.project.usf.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.project.usf.enums.EnumStatusSolicitation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "solicitation")
@Table(name = "solicitation")
public class SolicitationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long resource;

    private LocalDateTime requestedDate;

    private LocalDateTime answeredDate;

    @Enumerated(EnumType.STRING)
    private EnumStatusSolicitation statusSolicitation;

    private Long usf;

    private Long necessaryAmount;

}
