package uol.compass.project.usf.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.project.usf.enums.EnumStatusRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "request")
@Table(name = "request")
public class RequestEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idResource;

    private LocalDateTime requestedDate;

    private LocalDateTime answeredDate;

    @Enumerated(EnumType.STRING)
    private EnumStatusRequest statusRequest;

    @ManyToOne
    private UsfEntity idUsf;

    private Long necessaryAmount;

}
