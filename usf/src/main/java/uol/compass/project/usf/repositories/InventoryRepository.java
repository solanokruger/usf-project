package uol.compass.project.usf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.project.usf.entities.InventoryEntity;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
}
