package com.rizky.challenge4.backend.service.impl;

import com.rizky.challenge4.app.HasLogger;
import com.rizky.challenge4.app.security.EncoderConfig;
import com.rizky.challenge4.backend.exceptions.NotFoundExceptions;
import com.rizky.challenge4.backend.model.dto.UserDto;
import com.rizky.challenge4.backend.model.entity.Users;
import com.rizky.challenge4.backend.model.mapper.CreateUserAccount;
import com.rizky.challenge4.backend.repository.UsersRepository;
import com.rizky.challenge4.backend.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService, HasLogger {


    private final UsersRepository userRepository;


    private final CreateUserAccount createUserAccount;


    private final EncoderConfig encoderConfig;

    public UserServiceImpl(UsersRepository userRepository,
                           CreateUserAccount createUserAccount,
                           EncoderConfig encoderConfig) {
        this.userRepository = userRepository;
        this.createUserAccount = createUserAccount;
        this.encoderConfig = encoderConfig;
    }

    @Override
    public void addUser(UserDto user) {

            getLogger().info("User {} telah berhasil ditambahkan", user.getUsername());
            userRepository.save(createUserAccount.createUser(user));

    }

    @Override
    public void addUsers(List<Users> user) {
        getLogger().info("users has been successfully saved to the database");

        userRepository.saveAll(user);
    }

    @Override
    public Users updateUser(Users user) {

        getLogger().info("User {} has been successfully to update", user.getUsername());
        Users userUpdate = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundExceptions("User not found"));
        userUpdate.setUsername(user.getUsername());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(encoderConfig.passwordEncoder().encode(user.getPassword()));
        userUpdate.setAddress(user.getAddress());

        return userRepository.save(userUpdate);
    }

    @Override
    public Users findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptions("User " + id + " not found"));

    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> findAllUsers() {
        getLogger().info("User loaded successfully");
        return userRepository.findAll()
                .stream()
                .map(createUserAccount::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
