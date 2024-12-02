package com.security.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
@EnableMethodSecurity
public class SecurityConfig {
        //Configure the password encoder
        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
        

        //configure the security filter chain
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

            http.csrf().disable()
            .authorizeHttpRequests((authorize)->{authorize.requestMatchers("/api/v1/**")
            .permitAll();
            authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build();
        }

        //Configure userdetails
        @Bean
        public UserDetailsService userDetailsService(){
            UserDetails jhon = User.builder()
                .username("jhon")
                .password(passwordEncoder().encode("jhon@123"))
                .roles("USER")
                .build();

            UserDetails sam = User.builder()
                .username("sam")
                .password(passwordEncoder().encode("Sam@123"))
                .roles("ADMIN")
                .build();

            return new InMemoryUserDetailsManager(sam,jhon);
        }

}
