package com.blitmatthew.carshow484.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cor -> cor.disable())
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests( c -> c
                        .requestMatchers(HttpMethod.GET, "/api/v1/car/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/car/").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/car/").hasAnyRole("ADMIN")
                        .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("userPass"))
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("user1")
                .password(passwordEncoder().encode("userPass"))
                .roles("GUEST")
                .build();
        return new InMemoryUserDetailsManager(user, admin, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
