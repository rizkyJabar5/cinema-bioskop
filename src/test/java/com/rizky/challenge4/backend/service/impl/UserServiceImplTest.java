package com.rizky.challenge4.backend.service.impl;

import com.rizky.challenge4.backend.model.entity.Roles;
import com.rizky.challenge4.backend.model.entity.Users;
import com.rizky.challenge4.backend.model.enums.ERole;
import com.rizky.challenge4.backend.model.mapper.CreateUserAccount;
import com.rizky.challenge4.backend.repository.RolesRepository;
import com.rizky.challenge4.backend.repository.UsersRepository;
import com.rizky.challenge4.backend.service.UserService;
import org.assertj.core.util.Arrays;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@ExtendWith({MockitoExtension.class})
class UserServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private RolesRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    private CreateUserAccount createUserAccount;
    Users users = new Users();

    UserService userService;

    @BeforeEach
    void setUp() {

        this.userService = new UserServiceImpl(
                usersRepository,
                createUserAccount,
                passwordEncoder,
                roleRepository);
        users.setId(10L);
        users.setUsername("crikik");
        users.setPassword("mugiwara");
        users.setEmail("crikik@email.com");

//        List<Users> usersList = Arrays.asList();

    }

//    @AfterEach
//    public void tearDown() {
//        Mockito.verifyNoMoreInteractions(usersRepository);
//    }

    @DisplayName("Test will successfully for register new User")
    @Test
    void test_Register_User_Succeed() {
        Users newUser = new Users(
                "alya",
                "alya@email.com",
                "password");

        Roles role = new Roles(ERole.CUSTOMER);
        Mockito.when(roleRepository.findByRoleName(ERole.CUSTOMER))
                .thenReturn(Optional.of(role));

        userService.registerNewUser(newUser);
        Mockito.verify(usersRepository,
                Mockito.times(1)).save(newUser);
    }

    @Test
    @DisplayName("Test will fail to register new User because username and email is already.")
    void test_Register_User_With_Throws_If_UsernameOrEmail_Already_Taken() {

//        Roles role = new Roles(ERole.CUSTOMER);
//        Mockito.when(roleRepository.findByRoleName(ERole.CUSTOMER))
//                .thenReturn(Optional.of(role));

        Mockito.doThrow(new IllegalArgumentException(
                        String.format("%s username is already taken", users.getUsername())))
                .when(usersRepository).findByUsername(users.getUsername());
//        Mockito.when(usersRepository.findByUsername(users.getUsername()))
//                .thenThrow(new IllegalArgumentException(
//                        String.format("%s username is already taken", users.getUsername()))
//                );
//        Mockito.when(usersRepository.findByEmail(users.getEmail()))
//                .thenThrow(new IllegalArgumentException(
//                        String.format("%s email is already taken", users.getEmail().toUpperCase()))
//                );

//        Mockito.verify(usersRepository).save(users);


    }

    @Test
    void addUsers() {

        Users newUser = new Users();
        newUser.setId(895L);
        newUser.setUsername("masBin");
        newUser.setEmail("masbin@email.com");
        newUser.setPassword(passwordEncoder.encode("masBin"));
        newUser.setAddress("Surabaya");

        Collection<Roles> role = new HashSet<>();
        role.add("ADMIN");
        newUser.setRoles(role);
        Mockito.when(roleRepository.findByRoleName(ERole.ADMIN))
                .thenReturn(Optional.of(new Roles(ERole.ADMIN)));

        userService.addUsers(newUser);
        Mockito.verify(roleRepository,
                Mockito.times(2)).findByRoleName(ERole.ADMIN);
        Mockito.verify(usersRepository,
                Mockito.times(1)).save(newUser);

    }

    @Test
    void updateUser() {
    }

    @Test
    void findUserById() {

        Mockito.when(usersRepository.findById(users.getId()))
                .thenReturn(Optional.of(users));

        userService.findUserById(users.getId());
        Mockito.verify(usersRepository).findById(users.getId());
    }

    @Test
    void findByUsername() {

        Mockito.when(usersRepository.findByUsername(users.getUsername()))
                .thenReturn(Optional.of(users));

        userService.findByUsername(users.getUsername());
        Mockito.verify(usersRepository).findByUsername(users.getUsername());

    }

    @Test
    void deleteUserById() {

        userService.deleteUserById(users.getId());
        Mockito.verify(usersRepository).deleteById(users.getId());

    }

    @Test
    void findAllUsers() {
    }

    @Test
    void findByEmail() {

        Mockito.when(usersRepository.findByEmail(users.getEmail()))
                        .thenReturn(Optional.of(users));

        userService.findByEmail(users.getEmail());
        Mockito.verify(usersRepository).findByEmail(users.getEmail());

    }
}