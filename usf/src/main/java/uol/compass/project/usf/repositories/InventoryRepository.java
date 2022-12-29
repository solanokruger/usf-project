package uol.compass.project.usf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uol.compass.project.usf.model.entities.InventoryEntity;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
}
