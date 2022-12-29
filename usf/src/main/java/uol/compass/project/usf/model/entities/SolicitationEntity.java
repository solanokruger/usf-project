package uol.compass.project.usf.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.project.usf.model.enums.EnumStatusSolicitation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "solicitation")
@Table(name = "solicitation")
public class SolicitationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private ResourceEntity resource;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "answer_date")
    private LocalDateTime answerDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_solicitation")
    private EnumStatusSolicitation statusSolicitation;

    @ManyToOne
    @JoinColumn(name = "usf_id")
    private UsfEntity usf;

    @Column(name = "necessary_amount")
    private Long necessaryAmount;

    @PrePersist
    public void prePersist() {
        requestDate = LocalDateTime.now();
        statusSolicitation = EnumStatusSolicitation.PENDENTE;
    }

}
