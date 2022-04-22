package com.rizky.challenge4.backend.controller;

import com.rizky.challenge4.backend.model.dto.UserDto;
import com.rizky.challenge4.backend.model.entity.Users;
import com.rizky.challenge4.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody UserDto user){
        userService.addUser(user);
        return ResponseEntity.status(201).body(user.toString());
    }

    @PostMapping("/add-users")
    public ResponseEntity<String> addUsers(@RequestBody List<Users> user){
        userService.addUsers(user);
        return ResponseEntity.status(201).body(user.toString());
    }

    @GetMapping("/showall")
    public ResponseEntity<List<UserDto>> showAllUsers() {
        return ResponseEntity.status(202).body(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> showUsersById(@PathVariable("id") Long id) {
        return ResponseEntity.status(202).body(userService.findUserById(id));
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<Users> showUsersByUsername(@PathVariable String username) {
        return ResponseEntity.status(202).body(userService.findByUsername(username));
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<Users> showUsersByEmail(@PathVariable String email) {
        return ResponseEntity.status(202).body(userService.findByEmail(email));
    }

    @PutMapping("/update")
    public ResponseEntity<Users> updateUser(@RequestBody Users user){
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return ResponseEntity.accepted().body("Delete " + id + " succesfully");
    }
}
