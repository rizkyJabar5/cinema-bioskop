package com.rizky.cinematic.app.authentication.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JwtResponse {

    private String type = "Bearer";
    private Long id;
    private String token;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(Long id,
                       String token,
                       String username,
                       String email,
                       List<String> roles) {

        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(String token,
                       Long id,
                       String username,
                       String email,
                       List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
