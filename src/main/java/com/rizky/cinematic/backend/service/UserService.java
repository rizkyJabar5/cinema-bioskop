package com.rizky.cinematic.backend.service;

import com.rizky.cinematic.app.authentication.registration.SignupRequest;
import com.rizky.cinematic.backend.model.dto.UserDto;
import com.rizky.cinematic.backend.model.entity.Users;

import java.util.List;

public interface UserService {

    void registerNewUser(SignupRequest user);

    void addUsers(UserDto user);

    Users updateUser(UserDto user);

    Users findUserById(Long id);

    Users findByUsername(String username);

    void deleteUserById(Long id);

    List<UserDto> findAllUsers();

    Users findByEmail(String email);



}
