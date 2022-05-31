package com.rizky.cinematic.app.security.config;

import com.rizky.cinematic.backend.model.entity.Roles;
import com.rizky.cinematic.backend.model.enums.ERole;
import com.rizky.cinematic.backend.repository.RolesRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticatedUserTest {

    @Mock
    private RolesRepository roleRepository;

    @Test
    @Disabled
    void addRoles_With_Success_() {
        AuthenticatedUser authUser = new AuthenticatedUser(roleRepository);

        Roles roles = new Roles();

        for(ERole eRole: ERole.values()){
            roles.setRoleName(eRole);

//            when(roleRepository.save(roles)).thenReturn(roles);
        }

        authUser.preRun();

        verify(roleRepository, times(2))
                .save(roles);

    }
}
