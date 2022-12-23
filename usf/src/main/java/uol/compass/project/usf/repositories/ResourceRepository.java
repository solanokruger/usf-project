package uol.compass.project.usf.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uol.compass.project.usf.entities.ResourceEntity;
import uol.compass.project.usf.enums.EnumCategoryResource;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {
    Page<ResourceEntity> findAllByCategory(EnumCategoryResource category, Pageable pageable);
}
