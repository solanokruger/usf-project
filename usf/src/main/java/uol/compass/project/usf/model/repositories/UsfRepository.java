package uol.compass.project.usf.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uol.compass.project.usf.model.entities.UsfEntity;

public interface UsfRepository extends JpaRepository<UsfEntity, Long> {

}
