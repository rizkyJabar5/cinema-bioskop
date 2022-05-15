package com.rizky.challenge4.backend.config;

import com.rizky.challenge4.app.HasLogger;
import com.rizky.challenge4.backend.model.entity.Roles;
import com.rizky.challenge4.backend.model.enums.ERole;
import com.rizky.challenge4.backend.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticatedUser implements HasLogger {

    @Autowired
    private RolesRepository repository;

    @Bean
    public void prerun(){

        for (ERole eRole: ERole.values()){
            try {
                Roles role = repository.findByRoleName(eRole)
                        .orElseThrow(() -> new RuntimeException("Roles not found"));
            } catch(RuntimeException e){
                getLogger().info("Role " + eRole.name() + " is not found. Please create one...");
                Roles roleName = new Roles();
                roleName.setRoleName(eRole);
                repository.save(roleName);
            }
        }

    }
}
