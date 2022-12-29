package uol.compass.project.usf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.project.usf.model.entities.DoctorEntity;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
}
