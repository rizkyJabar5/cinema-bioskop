package com.rizky.challenge4.backend.service.impl;

import com.rizky.challenge4.app.HasLogger;
import com.rizky.challenge4.backend.exceptions.NotFoundExceptions;
import com.rizky.challenge4.backend.model.dto.UserDto;
import com.rizky.challenge4.backend.model.entity.Roles;
import com.rizky.challenge4.backend.model.entity.Users;
import com.rizky.challenge4.backend.model.enums.ERole;
import com.rizky.challenge4.backend.model.mapper.CreateUserAccount;
import com.rizky.challenge4.backend.repository.RolesRepository;
import com.rizky.challenge4.backend.repository.UsersRepository;
import com.rizky.challenge4.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, HasLogger {

    private final UsersRepository userRepository;
    private final CreateUserAccount createUserAccount;
    private final PasswordEncoder passwordEncoder;

    private final RolesRepository roleRepository;

    @Override
    public void registerNewUser(Users request) {

        boolean userExist = userRepository.findByUsername(request.getUsername()).isPresent();
        if (userExist) {
            getLogger().error("{} username is already taken", request.getUsername());
            throw new IllegalArgumentException(
                    String.format("%s username is already taken",
                            request.getUsername())
            );
        }

        boolean emailExist = userRepository.findByEmail(request.getEmail()).isPresent();
        if (emailExist) {
            getLogger().error("Email {} is already taken", request.getEmail());
            throw new IllegalArgumentException(
                    String.format("%s username is already taken",
                            request.getUsername())
            );
        }

        passwordEncoder.encode(request.getPassword());

        Collection<Roles> role = Collections.singleton(
                roleRepository.findByRoleName(ERole.CUSTOMER)
                        .orElseThrow(() -> new NotFoundExceptions(String.format(
                                "%s is not a valid role or %s is empty",
                                ERole.CUSTOMER, ERole.CUSTOMER)))
        );

        request.setRoles(role);
        getLogger().info("User {} telah berhasil ditambahkan", request.getUsername());
        userRepository.save(request);
    }

    @Override
    public void addUsers(Users register) {

        passwordEncoder.encode(register.getPassword());

        Collection<Roles> getAllRoles = register.getRoles();
        Set<Roles> roles = new HashSet<>();

        if (getAllRoles == null) {
            Roles defaultRole = roleRepository.findByRoleName(ERole.CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(defaultRole);
        } else {
            getAllRoles.forEach(role -> {
                Roles allRoles = roleRepository.findByRoleName(ERole.valueOf(ERole.getAllRoles()))
                        .orElseThrow(() -> new RuntimeException("Error: Role " + role + "  is not found"));
                roles.add(allRoles);
            });
        }

        register.setRoles(roles);
        getLogger().info("users has been successfully saved to the database");
        userRepository.save(register);
    }

    @Override
    public Users updateUser(Users user) {

        getLogger().info("User {} has been successfully to update", user.getUsername());
        Users userUpdate = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundExceptions("User not found"));
        userUpdate.setUsername(user.getUsername());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        userUpdate.setAddress(user.getAddress());

        return userRepository.save(userUpdate);
    }

    @Override
    public Users findUserById(Long id) {
        getLogger().info("User: {} is available", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptions("User " + id + " not found"));

    }

    @Override
    public Users findByUsername(String username) {
        getLogger().info("Found User with username: {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    @Override
    public void deleteUserById(Long id) {
        getLogger().info("Id {} has been removed on database", id);
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> findAllUsers() {
        getLogger().info("User loaded successfully");
        return userRepository.findAll()
                .stream()
                .map(createUserAccount::convertEntityToDto)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Users findByEmail(String email) {
        getLogger().info("Found user with email: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new NotFoundExceptions("Error: Not Found user with email: " + email));
    }


}
