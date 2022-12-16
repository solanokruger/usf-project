package uol.compass.project.usf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.project.usf.entities.DoctorEntity;

import java.util.List;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    List<DoctorEntity> findByIdTeam(Long id);
}
