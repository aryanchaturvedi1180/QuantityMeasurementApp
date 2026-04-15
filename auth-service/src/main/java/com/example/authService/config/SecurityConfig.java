package com.example.authService.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.authService.security.JwtFilter;
import com.example.authService.security.OAuth2SuccessHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/v1/users/**").permitAll()
            .requestMatchers("/oauth2/**", "/login/**").permitAll()

            // ONLY THIS endpoint secured
//            .requestMatchers("/api/v1/quantities/history/**").authenticated()

            // rest quantities open
//            .requestMatchers("/api/v1/quantities/**").permitAll()

            .anyRequest().permitAll()
        )
        .oauth2Login(oauth -> oauth
            .successHandler(oAuth2SuccessHandler)
        )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
        
        
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
