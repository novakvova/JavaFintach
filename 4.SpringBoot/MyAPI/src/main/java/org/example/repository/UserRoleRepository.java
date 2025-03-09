package org.example.repository;

import org.example.entities.UserEntity;
import org.example.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    List<UserRoleEntity> findByUser(UserEntity User);
}
