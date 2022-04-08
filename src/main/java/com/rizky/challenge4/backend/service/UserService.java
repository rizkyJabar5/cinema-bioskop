package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.data.dto.UserDto;
import com.rizky.challenge4.backend.data.entity.Users;

import java.util.List;

public interface UserService {

    Users addUser(Users user);

    List<Users> addUsers(List<Users> user);

    Users updateUser(Users User);

    Users findUserById(Long id);

    Users findByUsername(String username);

    String deleteUserById(Long id);

    List<UserDto> findAllUsers();

    Users findByEmail(String email);
}
