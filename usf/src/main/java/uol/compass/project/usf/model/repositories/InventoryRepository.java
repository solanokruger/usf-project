package uol.compass.project.usf.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.project.usf.model.entities.InventoryEntity;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
}
