package com.rizky.cinematic.backend.model.mapper;

import com.rizky.cinematic.app.authentication.registration.SignupRequest;
import com.rizky.cinematic.backend.exceptions.NotFoundExceptions;
import com.rizky.cinematic.backend.model.dto.UserDto;
import com.rizky.cinematic.backend.model.entity.Roles;
import com.rizky.cinematic.backend.model.entity.Users;
import com.rizky.cinematic.backend.model.enums.ERole;
import com.rizky.cinematic.backend.repository.RolesRepository;
import com.rizky.cinematic.backend.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    private final UsersRepository userRepository;

    private final RolesRepository roleRepository;

    public UserDto convertEntityToDto(Users user) {
        UserDto userdto = new UserDto();
        userdto.setUserId(user.getId());
        userdto.setUsername(user.getUsername());
        userdto.setEmail(user.getEmail());
        userdto.setPassword(passwordEncoder.encode(user.getPassword()));
        userdto.setAddress(user.getAddress());
        return userdto;
    }

    /**
     * This method for convert entity to SignupRequestDto for Auth Controller
     * or Service.
     * @param request used to create the new user for registration service
     * @return entity for saving in database
     */
    public Users registerUser(SignupRequest request) {

        Users entity = new Users();

        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));

        addRoleToUsers(entity, request.getRoles());
        return entity;
    }

    /**
     * This method convert entity Users to Users Dto for easy
     * to handling on controller
     * @param request for adding new user with mapper entity Users
     * @return entity for saving in database
     */
    public Users addNewUser(UserDto request) {

        Users entity = new Users();

        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setAddress(request.getAddress());
        entity.setJoinDate(new Date(System.currentTimeMillis()));

        addRoleToUsers(entity, request.getRoles());
        return entity;
    }

    public Users updateExistingUser(UserDto request) {

        Users update = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundExceptions("User not found"));

        update.setUsername(request.getUsername());
        update.setEmail(request.getEmail());
        update.setPassword(passwordEncoder.encode(request.getPassword()));
        update.setAddress(request.getAddress());
        addRoleToUsers(update, request.getRoles());
        return update;
    }

    private void addRoleToUsers(Users users, Set<String> request) {

        Set<Roles> roles = new HashSet<>();

        if (request == null) {
            Roles defaultRole = roleRepository.findByRoleName(ERole.CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(defaultRole);
        } else {
            request.forEach(role -> {
                Roles allRoles = roleRepository.findByRoleName(ERole.valueOf(role))
                        .orElseThrow(() -> new RuntimeException("Error: Role " + role + "  is not found"));
                roles.add(allRoles);
            });
        }

        users.setRoles(roles);
    }
}
