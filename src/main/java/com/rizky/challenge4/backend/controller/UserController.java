package com.rizky.challenge4.backend.controller;

import com.rizky.challenge4.backend.model.dto.UserDto;
import com.rizky.challenge4.backend.model.entity.Users;
import com.rizky.challenge4.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users Controller", description = "API for handling transaction Users CRUD operation entity in the Cinematic database.")
@RestController
@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "This method function to add user detailing.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Add user to store the database ",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "UserDto{" +
                                    "id= 1, " +
                                    "username:rizky, " +
                                    "email:rizky@email.com, " +
                                    "password:manusia, " +
                                    "address: Surabaya" +
                                    '}'))})})
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserDto user) {
        userService.addUser(user);
        return ResponseEntity.status(201).body(user.toString());
    }

    @Operation(summary = "This method function to add many user detailing.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Add user to store the database ",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "UserDto{" +
                                    "id= 1, " +
                                    "username:rizky, " +
                                    "email:rizky@email.com, " +
                                    "password:manusia, " +
                                    "address: Surabaya" +
                                    '}'))})})
    @PostMapping("/add/many-users")
    public ResponseEntity<String> addUsers(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Created user object", required = true)
// Swagger Custom response body
            @RequestBody List<Users> user) {
        userService.addUsers(user);
        return ResponseEntity.status(201).body(user.toString());
    }

    @Operation(summary = "Get or show all the data entity from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Get all user on the database",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "[{" +
                                    "id= 1, " +
                                    "username:rizky, " +
                                    "email:rizky@email.com, " +
                                    "password:manusia, " +
                                    "address: Surabaya" +
                                    '}' +
                                    "id= 2, " +
                                    "username:khamet, " +
                                    "email:khamet@email.com, " +
                                    "password:akuganteng, " +
                                    "address: Malang" +
                                    "}]"))
                    })
    })
    @GetMapping("/search/showall")
    public ResponseEntity<List<UserDto>> showAllUsers() {
        return ResponseEntity.status(202).body(userService.findAllUsers());
    }

    @Operation(summary = "Get or search the data by id from the entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Get user by id on the database",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{id= 1, username:rizky, email:rizky@email.com, password:manusia, address: Surabaya}"))
                    })
    })
    @GetMapping("/search/{id}")
    public ResponseEntity<Users> showUsersById(
            @Parameter(description = "add id for get the user")
            @PathVariable("id") Long id) {
        return ResponseEntity.status(202).body(userService.findUserById(id));
    }

    @Operation(summary = "Get or search user by parameter username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Get user by username with parameter",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{id= 1, username:rizky, email:rizky@email.com, password:manusia, address: Surabaya}"))
                    })
    })
    @GetMapping("/search/{username}")
    public ResponseEntity<Users> showUsersByUsername(
            @Parameter(description = "add parameter for get the user")
            @PathVariable String username) {
        return ResponseEntity.status(202).body(userService.findByUsername(username));
    }

    @Operation(summary = "Get or search user by parameter email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Get user by email parameter",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{id= 1, username:rizky, email:rizky@email.com, password:manusia, address: Surabaya}")
                    )
                    })
    })
    @GetMapping("/search/{email}")
    public ResponseEntity<Users> showUsersByEmail(
            @Parameter(description = "add parameter email for get the user")
            @PathVariable String email) {
        return ResponseEntity.status(202).body(userService.findByEmail(email));
    }

    @Operation(summary = "This method function to edit user detail.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update user on the database with match by id",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "UserDto{" +
                                    "id= 1, " +
                                    "username:rizky, " +
                                    "email:rizky@email.com, " +
                                    "password:manusia, " +
                                    "address: Surabaya" +
                                    '}'))
                    })
    })
    @PutMapping("/update")
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    @Operation(summary = "Delete user by calling id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Delete user by id with parameter",
                    content = {@Content(
                            schema = @Schema(example = "Delete user id 3 has successfully."))
                    })
    })
    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteUserById(
            @Parameter(description = "add id for delete the user")
            @PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.accepted().body("Delete " + id + " succesfully");
    }
}
