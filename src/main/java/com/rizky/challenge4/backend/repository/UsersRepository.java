package com.rizky.challenge4.backend.repository;

import com.rizky.challenge4.backend.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    Users findByEmail(String email);
}
