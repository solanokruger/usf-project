package uol.compass.project.usf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import uol.compass.project.usf.model.entities.UsfEntity;

@Repository
public interface UsfRepository extends JpaRepository<UsfEntity, Long> {

}
