package com.rizky.challenge4.backend.controller;

import com.rizky.challenge4.backend.data.dto.UserDto;
import com.rizky.challenge4.backend.data.entity.Users;
import com.rizky.challenge4.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public String addUser(@RequestBody Users user){
        userService.addUser(user);
        return user.toString();
    }

    @PostMapping("/users")
    public String addUsers(@RequestBody List<Users> user){
        userService.addUsers(user);
        return user.toString();
    }

    @GetMapping("/users")
    public List<UserDto> showAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public Users showUsersById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/user-name/{username}")
    public Users showUsersByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/user-email/{email}")
    public Users showUsersByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PutMapping("/user-update")
    public Users updateUser(@RequestBody Users user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUserById(@PathVariable("id") Long id){
        return userService.deleteUserById(id);
    }
}
