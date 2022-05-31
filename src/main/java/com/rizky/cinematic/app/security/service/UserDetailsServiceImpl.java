package com.rizky.cinematic.app.security.service;

import com.rizky.cinematic.app.HasLogger;
import com.rizky.cinematic.backend.model.entity.Users;
import com.rizky.cinematic.backend.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, HasLogger {

    private final UsersRepository userRepository;

    public UserDetailsServiceImpl(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        getLogger().info("Successfully loaded user");
        return UserDetailsImpl.build(user);

    }

}
