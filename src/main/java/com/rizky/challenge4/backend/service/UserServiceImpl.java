package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.model.dto.UserDto;
import com.rizky.challenge4.backend.model.entity.Users;
import com.rizky.challenge4.backend.model.mapper.UserConvertToDto;
import com.rizky.challenge4.backend.error.NotFoundExceptions;
import com.rizky.challenge4.backend.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private UserConvertToDto userConvertToDto;

    @Override
    public void addUser(UserDto user) {
        Users users = new Users();
        users.setUsername(user.getUsername());
        users.setEmail(user.getEmail());
        users.setPassword(user.getPassword());
        users.setAddress(user.getAddress());
        log.info("User {} telah berhasil ditambahkan", users.getUsername());
        userRepository.save(users);
    }

    @Override
    public void addUsers(List<Users> user) {
        log.info("users has been successfully saved to the database");
        userRepository.saveAll(user);
    }

    @Override
    public Users updateUser(Users user) {
        log.info("User {} has been successfully to update", user.getUsername());
        Users userUpdate = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundExceptions("User not found"));
        userUpdate.setUsername(user.getUsername());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(user.getPassword());
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
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> findAllUsers() {
        log.info("User loaded successfully");
        return userRepository.findAll()
                .stream()
                .map(userConvertToDto::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
