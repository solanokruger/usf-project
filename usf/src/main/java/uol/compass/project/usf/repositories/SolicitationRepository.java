package uol.compass.project.usf.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import uol.compass.project.usf.model.entities.SolicitationEntity;
import uol.compass.project.usf.model.enums.EnumStatusSolicitation;

@Repository
public interface SolicitationRepository extends JpaRepository<SolicitationEntity, Long> {

    Page<SolicitationEntity> findAllByStatusSolicitation(EnumStatusSolicitation status, Pageable pageable);
}
