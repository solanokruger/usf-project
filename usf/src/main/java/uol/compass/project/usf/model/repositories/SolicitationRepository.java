package uol.compass.project.usf.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uol.compass.project.usf.model.entities.SolicitationEntity;

public interface SolicitationRepository extends JpaRepository<SolicitationEntity, Long> {

}
