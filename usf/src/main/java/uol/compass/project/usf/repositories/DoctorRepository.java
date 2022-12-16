package uol.compass.project.usf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.project.usf.entities.DoctorEntity;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
}
