package com.rizky.challenge4.backend.data.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long userId;
    private String username;
    private String email;
    private String password;
    private String address;

}
