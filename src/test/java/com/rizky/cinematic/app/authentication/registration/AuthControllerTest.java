package com.rizky.cinematic.app.authentication.registration;

import com.rizky.cinematic.app.security.filter.JwtUtils;
import com.rizky.cinematic.backend.repository.UsersRepository;
import com.rizky.cinematic.backend.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserService userservice;
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private JwtUtils jwtUtils;
    AuthController authController;
    SignupRequest signupRequest;

    @BeforeEach
    void setUp() {
        this.authController = new AuthController(
                authenticationManager,
                userservice,
                usersRepository,
                jwtUtils);

        signupRequest = new SignupRequest();
        signupRequest.setUsername("admin");
        signupRequest.setEmail("admin@email.com");
        signupRequest.setPassword("admin");
    }

    @Test
    void testSignup() {
        Set<String> roles = new HashSet<>();
        roles.add("ADMIN");

        signupRequest = new SignupRequest();
        signupRequest.setUsername("santai");
        signupRequest.setEmail("santai@email.com");
        signupRequest.setPassword("santai");
        signupRequest.setRoles(roles);

        Assertions.assertEquals("User registered successfully",
                Objects.requireNonNull(authController
                                .registerUser(signupRequest)
                                .getBody())
                        .getMessage());
    }

    @Test
    void signUpUsernameExists() {

        SignupRequest request = Mockito.spy(signupRequest);
        request.setUsername("admin");
        request.setEmail("admin@email.com");
        request.setPassword("admin");

        Mockito.when(usersRepository.existsByUsername("admin"))
                .thenReturn(Boolean.TRUE);

        Assertions.assertEquals("Error: Username is already taken!",
                Objects.requireNonNull(authController
                                .registerUser(request)
                                .getBody())
                        .getMessage());
    }

    @Test
    void signUpEmailExists() {

        SignupRequest request = Mockito.spy(signupRequest);
        request.setUsername("admin");
        request.setEmail("admin@email.com");
        request.setPassword("admin");

        Mockito.when(usersRepository.existsByUsername("admin"))
                .thenReturn(Boolean.FALSE);
        Mockito.when(usersRepository.existsByEmail("admin@email.com"))
                .thenReturn(Boolean.TRUE);

        Assertions.assertEquals("Error: Email is already taken!",
                Objects.requireNonNull(authController
                                .registerUser(request)
                                .getBody())
                        .getMessage());
    }

}









