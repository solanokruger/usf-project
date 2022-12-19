package uol.compass.project.usf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.project.usf.entities.ResourceEntity;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {
}
