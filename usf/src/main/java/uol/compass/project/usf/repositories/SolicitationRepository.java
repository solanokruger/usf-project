package uol.compass.project.usf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uol.compass.project.usf.model.entities.SolicitationEntity;
import uol.compass.project.usf.model.enums.EnumStatusSolicitation;

import java.util.List;

public interface SolicitationRepository extends JpaRepository<SolicitationEntity, Long> {


}
