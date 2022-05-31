package com.rizky.cinematic.backend.service.impl;

import com.rizky.cinematic.app.authentication.registration.SignupRequest;
import com.rizky.cinematic.backend.exceptions.NotFoundExceptions;
import com.rizky.cinematic.backend.model.dto.UserDto;
import com.rizky.cinematic.backend.model.entity.Roles;
import com.rizky.cinematic.backend.model.entity.Users;
import com.rizky.cinematic.backend.model.enums.ERole;
import com.rizky.cinematic.backend.model.mapper.UserMapper;
import com.rizky.cinematic.backend.repository.UsersRepository;
import com.rizky.cinematic.backend.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class UserServiceImplTest {

    @Mock
    UsersRepository usersRepository;
    @Mock
    UserMapper userMapper;
    Users users;

    UserService userService;

    @BeforeEach
    void setUp() {

        userService = new UserServiceImpl(
                usersRepository,
                userMapper);

        users = new Users();
        users.setId(21L);
        users.setUsername("crikik");
        users.setEmail("criki@email.com");
        users.setPassword("mugiwara");
        users.setAddress("Surabaya");
        users.setRoles(new HashSet<>(Collections.singleton(new Roles(ERole.ADMIN))));


    }

    @DisplayName("Test will successfully for register new User")
    @Test
    void test_Register_User_Succeed() {

        Set<String> roleAdmin = new HashSet<>();
        roleAdmin.add("ADMIN");

        SignupRequest newUser = new SignupRequest();
        newUser.setUsername("crikik");
        newUser.setEmail("criki@email.com");
        newUser.setPassword("mugiwara");
        newUser.setRoles(roleAdmin);

        users = new Users(
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getPassword(),
                new HashSet<>(Collections.singleton(new Roles(ERole.ADMIN))));

        Set<String> stringStream = users.getRoles()
                .stream()
                .map(roles -> roles.getRoleName().toString())
                .collect(Collectors.toSet());

        when(usersRepository.save(userMapper.registerUser(newUser)))
                .thenReturn(users);

        userService.registerNewUser(newUser);

        verify(usersRepository, times(1))
                .save(userMapper.registerUser(newUser));
        Assertions.assertEquals(newUser.getRoles(), stringStream);
    }

    @Test
    @DisplayName("Register New User for validation username - Test will success if username already taken.")
    void test_Register_User_With_Throws_If_Username_Already_Taken() {

        Users newUser = spy(users);

        SignupRequest request = new SignupRequest();
        request.setUsername(newUser.getUsername());
        request.setEmail("maell@email.com");
        request.setPassword("malammma");

        when(usersRepository.findByUsername(request.getUsername()))
                .thenReturn(Optional.of(users));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.registerNewUser(request));

        verify(usersRepository, times(1))
                .findByUsername(newUser.getUsername());

    }

    @Test
    @DisplayName("Register New User - Test will success if email already taken.")
    void test_Register_User_With_Throws_If_Email_Already_Taken() {

        Users newUser = spy(users);

        SignupRequest request = new SignupRequest();
        request.setUsername("maramara");
        request.setEmail(newUser.getEmail());
        request.setPassword("akuGanteng");

        when(usersRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(users));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.registerNewUser(request));

        verify(usersRepository, times(1))
                .findByEmail(newUser.getEmail());

    }

    @Test
    @DisplayName("Adding New User - Test will success for add new user.")
    void addUsers() {

        Set<String> roleCustomer = new HashSet<>();
        roleCustomer.add("CUSTOMER");

        UserDto request = new UserDto();
        request.setUsername("maramara");
        request.setPassword("akuGanteng");
        request.setRoles(roleCustomer);

        users = new Users(
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                new HashSet<>(Collections.singleton(new Roles(ERole.CUSTOMER))));
        when(usersRepository.save(userMapper.addNewUser(request)))
                .thenReturn(users);
        Set<String> entityRole = users.getRoles()
                .stream()
                .map(role -> role.getRoleName().toString())
                .collect(Collectors.toSet());

        userService.addUsers(request);
        verify(usersRepository).save(userMapper.addNewUser(request));
        Assertions.assertEquals(request.getRoles(), entityRole);

    }

    @Test
    @DisplayName("Update for existing user - Test will success for update user.")
    void updateUser() {

        Users spy = spy(users);

        UserDto userDto = new UserDto();
        userDto.setUsername("maramara");
        userDto.setPassword(spy.getPassword());
        userDto.setEmail(spy.getEmail());

        userService.updateUser(userDto);

        verify(usersRepository, times(1))
                .save(userMapper.updateExistingUser(userDto));

    }

    @Test
    @DisplayName("Test for find user by ID.")
    void findUserById() {

        when(usersRepository.findById(users.getId()))
                .thenReturn(Optional.of(users));

        userService.findUserById(users.getId());
        verify(usersRepository).findById(users.getId());
    }

    @Test
    void findAllUsers() {
        List<Users> userList = Arrays.asList(
                new Users("kiki", "kiki@email.com", "lolo", "Malang", new HashSet<>()),
                new Users("markonah", "markonah@email.com", "markolo", "Malang", new HashSet<>()),
                new Users("marah", "marah@email.com", "ngamuk", " Surabaya", new HashSet<>()),
                new Users("aku", "aku@email.com", "ajkyuuu", "Purbalingga", new HashSet<>())
        );

        for(Users value : userList){
            Users users1 = new Users();
            users1.setUsername(value.getUsername());
            users1.setPassword(value.getPassword());
            users1.setEmail(value.getEmail());
            users1.setAddress(value.getAddress());
            when(usersRepository.findAll())
                    .thenReturn(Collections.singletonList(users1));
        }

        userService.findAllUsers();
        verify(usersRepository).findAll();

    }

    @Test
    @DisplayName("Test for find user by username.")
    void findByUsername() {

        when(usersRepository.findByUsername(users.getUsername()))
                .thenReturn(Optional.of(users));

        userService.findByUsername(users.getUsername());
        verify(usersRepository).findByUsername(users.getUsername());

    }

    @Test
    @DisplayName("Test for delete user by ID.")
    void deleteUserById() {

        userService.deleteUserById(users.getId());
        verify(usersRepository).deleteById(users.getId());

    }

    @Test
    @DisplayName("Test for find user by email.")
    void findByEmail() {

        when(usersRepository.findByEmail(users.getEmail()))
                .thenReturn(Optional.of(users));

        userService.findByEmail(users.getEmail());
        verify(usersRepository).findByEmail(users.getEmail());

    }

    @ParameterizedTest
    @ValueSource(strings = "slowkyman@mail.com")
    @DisplayName("Test for find email but is return success if email doesn't exists.")
    void email_Is_Not_Found(String email) {

        Assertions.assertThrows(NotFoundExceptions.class,
                () -> userService.findByEmail(email));

    }
}