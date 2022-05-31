package com.rizky.cinematic.app.authentication.registration;


import com.rizky.cinematic.app.authentication.response.JwtResponse;
import com.rizky.cinematic.app.authentication.response.MessageResponse;
import com.rizky.cinematic.app.security.filter.JwtUtils;
import com.rizky.cinematic.app.security.service.UserDetailsImpl;
import com.rizky.cinematic.backend.repository.UsersRepository;
import com.rizky.cinematic.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Authentication Controller", description = "API for managing authentication in Cinematic")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userservice;
    private final UsersRepository usersRepository;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userservice, UsersRepository usersRepository,
                          JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userservice = userservice;
        this.usersRepository = usersRepository;
        this.jwtUtils = jwtUtils;
    }

    @Operation(summary = "This API for handling login to application")
    @ApiResponses(
            value = {@ApiResponse(
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = JwtResponse.class)))
                    })
            })
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request login.", required = true,
                    content = @Content(
                            schema = @Schema(implementation = LoginRequest.class)))
            @Valid @RequestBody Map<String, Object> login) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.get("username"), login.get("password")));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }


    @Operation(summary = "This API for handling register new user to application")
    @ApiResponses(
            value = {@ApiResponse(
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = MessageResponse.class)))
                    })
            })
    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request login.", required = true,
                    content = @Content(
                            schema = @Schema(implementation = SignupRequest.class)))
            @Valid @RequestBody SignupRequest signupRequest) {

        boolean existsByUsername = usersRepository.existsByUsername(signupRequest.getUsername());
        if (existsByUsername) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        boolean existsByEmail = usersRepository.existsByEmail(signupRequest.getEmail());
        if (existsByEmail) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
        }

        userservice.registerNewUser(signupRequest);
        return ResponseEntity.ok().body(new MessageResponse("User registered successfully"));
    }

}
