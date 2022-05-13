package com.rizky.challenge4.backend.repository;

import com.rizky.challenge4.backend.model.entity.Roles;
import com.rizky.challenge4.backend.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByRoleName(ERole roleName);

}
