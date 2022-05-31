package com.rizky.cinematic.app.security.config;

import com.rizky.cinematic.app.HasLogger;
import com.rizky.cinematic.backend.model.entity.Roles;
import com.rizky.cinematic.backend.model.enums.ERole;
import com.rizky.cinematic.backend.repository.RolesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticatedUser implements HasLogger {

    private final RolesRepository repository;

    public AuthenticatedUser(RolesRepository repository) {
        this.repository = repository;
    }

    @Bean
    public void preRun(){

        for (ERole eRole: ERole.values()){
            try {
                Roles role = repository.findByRoleName(eRole)
                        .orElseThrow(() -> new RuntimeException("Roles not found"));
                getLogger().error("{} not found", role);
            } catch(RuntimeException e){
                String msg = "Role " + eRole.name() + " is not found. Please create one...";
                getLogger().info(msg);

                Roles roleName = new Roles();
                roleName.setRoleName(eRole);
                repository.save(roleName);
            }
        }

    }
}
