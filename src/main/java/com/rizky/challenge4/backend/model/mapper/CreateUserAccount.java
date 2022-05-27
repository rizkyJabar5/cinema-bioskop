package com.rizky.challenge4.backend.model.mapper;

import com.rizky.challenge4.app.security.EncoderConfig;
import com.rizky.challenge4.backend.model.dto.UserDto;
import com.rizky.challenge4.backend.model.entity.Roles;
import com.rizky.challenge4.backend.model.entity.Users;
import com.rizky.challenge4.backend.model.enums.ERole;
import com.rizky.challenge4.backend.repository.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class CreateUserAccount {

    private final EncoderConfig encode;

    private final RolesRepository roleRepository;

    public UserDto convertEntityToDto(Users user) {
        UserDto userdto = new UserDto();
        userdto.setUserId(user.getId());
        userdto.setUsername(user.getUsername());
        userdto.setEmail(user.getEmail());
        userdto.setPassword(encode.passwordEncoder().encode(user.getPassword()));
        userdto.setAddress(user.getAddress());
        return userdto;
    }

    public Users createUser(UserDto request) {

        Users entity = new Users(
                request.getUsername(),
                request.getEmail(),
                encode.passwordEncoder().encode(request.getPassword())
        );

        Set<Roles> getAllRoles = request.getRoles();
        Set<Roles> roles = new HashSet<>();

        if (getAllRoles == null) {
            Roles defaultRole = roleRepository.findByRoleName(ERole.CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(defaultRole);
        } else {
            getAllRoles.forEach(role -> {
                Roles allRoles = roleRepository.findByRoleName(ERole.valueOf(ERole.getAllRoles()))
                        .orElseThrow(() -> new RuntimeException("Error: Role " + role + "  is not found"));
                roles.add(allRoles);
            });
            entity.setRoles(roles);
        }
        return entity;
    }
}
