package uol.compass.project.usf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uol.compass.project.usf.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
    
}
