package com.rizky.cinematic.backend.service.impl;

import com.rizky.cinematic.app.HasLogger;
import com.rizky.cinematic.app.authentication.registration.SignupRequest;
import com.rizky.cinematic.backend.exceptions.NotFoundExceptions;
import com.rizky.cinematic.backend.model.dto.UserDto;
import com.rizky.cinematic.backend.model.entity.Users;
import com.rizky.cinematic.backend.model.mapper.UserMapper;
import com.rizky.cinematic.backend.repository.UsersRepository;
import com.rizky.cinematic.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, HasLogger {

    private final UsersRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void registerNewUser(SignupRequest request) {

        checkUsernameAndEmailExist(
                request.getUsername(),
                request.getEmail()
        );

        getLogger().info("User {} has been successfully to register", request.getUsername());
        userRepository.save(userMapper.registerUser(request));
    }

    @Override
    public void addUsers(UserDto register) {

        checkUsernameAndEmailExist(
                register.getUsername(),
                register.getEmail()
        );
        getLogger().info("User {} has been successfully saved to the database", register.getUsername());
        userRepository.save(userMapper.addNewUser(register));

    }

    @Override
    public Users updateUser(UserDto request) {

        getLogger().info("User {} has been successfully to update", request.getUsername());
        return userRepository.save(userMapper.updateExistingUser(request));

    }

    @Override
    public Users findUserById(Long id) {
        getLogger().info("User with id: {} has found", id);
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
                .map(userMapper::convertEntityToDto)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new NotFoundExceptions("Error: Not Found user with email: " + email));
    }

    private void checkUsernameAndEmailExist(String username, String email) {
        boolean userExist = userRepository.findByUsername(username).isPresent();
        if (userExist) {
            getLogger().error("{} username is already taken", username);
            throw new IllegalArgumentException(
                    String.format("%s username is already taken", username)
            );
        }

        boolean emailExist = userRepository.findByEmail(email).isPresent();
        if (emailExist) {
            getLogger().error("Email {} is already taken", email);
            throw new IllegalArgumentException(
                    String.format("%s username is already taken", email)
            );
        }
    }
}
