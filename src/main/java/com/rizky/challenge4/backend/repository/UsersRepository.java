package com.rizky.challenge4.backend.repository;

import com.rizky.challenge4.backend.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

    Users findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
