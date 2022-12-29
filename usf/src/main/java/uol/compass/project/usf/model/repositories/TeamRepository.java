package uol.compass.project.usf.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uol.compass.project.usf.model.entities.TeamEntity;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

}
