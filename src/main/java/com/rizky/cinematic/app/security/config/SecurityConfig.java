package com.rizky.cinematic.app.security.config;

import com.rizky.cinematic.app.security.filter.AuthEntryPointJwt;
import com.rizky.cinematic.app.security.filter.AuthTokenFilter;
import com.rizky.cinematic.app.security.service.UserDetailsServiceImpl;
import com.rizky.cinematic.backend.model.enums.ERole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final EncoderConfig encoderConfig;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, AuthEntryPointJwt unauthorizedHandler, EncoderConfig encoderConfig) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.encoderConfig = encoderConfig;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoderConfig.passwordEncoder());
    }

//    --------------HTTP SECURITY-----------
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                    .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .antMatchers("/api/v1/films/admin/**").hasAuthority(ERole.ADMIN.name())
                .antMatchers("/api/v1/schedules/add/**").hasAuthority(ERole.ADMIN.name())
                .antMatchers("/api/v1/users/admin/**").hasAuthority(ERole.ADMIN.name())

                .antMatchers("/api/v1/schedules/public/**").hasAnyAuthority(ERole.CUSTOMER.name(), ERole.ADMIN.name())
                .antMatchers("/api/v1/users/public/**").hasAnyAuthority(ERole.CUSTOMER.name(), ERole.ADMIN.name())
                .antMatchers("/api/v1/films/public/**").hasAnyAuthority(ERole.CUSTOMER.name(), ERole.ADMIN.name())

                .antMatchers("/api/v1/seats/**").hasAnyAuthority(ERole.ADMIN.name(), ERole.CUSTOMER.name())
                .antMatchers("/api/v1/invoice/**").hasAnyAuthority(ERole.ADMIN.name(), ERole.CUSTOMER.name())
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
