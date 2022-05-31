package com.rizky.cinematic.app.authentication.registration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginRequestTest {

    LoginRequest request = new LoginRequest();

    @Test
    void setAndGetRequest_withCorrectValue() {
        request.setUsername("kamet");
        request.setPassword("mugiwara");

        Assertions.assertEquals("kamet", request.getUsername());

        Assertions.assertEquals("mugiwara", request.getPassword());
    }

}