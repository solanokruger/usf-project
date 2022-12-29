package uol.compass.project.usf.model.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uol.compass.project.usf.model.entities.ResourceEntity;
import uol.compass.project.usf.model.enums.EnumCategoryResource;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {
    Page<ResourceEntity> findAllByCategory(EnumCategoryResource category, Pageable pageable);
}
