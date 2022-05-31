package com.rizky.cinematic.backend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String username;
    private String email;
    private String password;
    private String address;
    private Date joinedDate;
    private Set<String> roles;

    public UserDto(String username,
                   String email,
                   String password,
                   String address,
                   Set<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.roles = roles;
    }

    public UserDto(String username, String email, String password, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", joinedDate=" + joinedDate +
                ", roles=" + roles +
                '}';
    }
}
