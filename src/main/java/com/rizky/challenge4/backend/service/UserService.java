package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.model.dto.UserDto;
import com.rizky.challenge4.backend.model.entity.Users;

import java.util.List;

public interface UserService {

    void addUser(UserDto user);

    void addUsers(List<Users> user);

    Users updateUser(Users User);

    Users findUserById(Long id);

    Users findByUsername(String username);

    void deleteUserById(Long id);

    List<UserDto> findAllUsers();

    Users findByEmail(String email);
}
