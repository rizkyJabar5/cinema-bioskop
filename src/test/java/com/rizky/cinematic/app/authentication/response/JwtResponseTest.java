package com.rizky.cinematic.app.authentication.response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class JwtResponseTest {

    private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzd";

    @Test
    void setAndGetResponse_withCorrectValue() {

        List<String> roleAdmin = new LinkedList<>();
        roleAdmin.add("admin");

        JwtResponse jwt = new JwtResponse();
        jwt.setId(1L);
        jwt.setToken(TOKEN);
        jwt.setUsername("malam");
        jwt.setEmail("malam@email.com");
        jwt.setRoles(roleAdmin);

        JwtResponse jwtConstruct = new JwtResponse(
                1L,
                TOKEN,
                "malam",
                "malam@email.com",
                roleAdmin
        );

        Assertions.assertEquals(1L, jwt.getId());
        Assertions.assertEquals(TOKEN, jwt.getToken());
        Assertions.assertEquals("malam", jwt.getUsername());
        Assertions.assertEquals("malam@email.com", jwt.getEmail());
        Assertions.assertEquals(roleAdmin, jwt.getRoles());
        Assertions.assertNotEquals(jwt, jwtConstruct);


    }
}