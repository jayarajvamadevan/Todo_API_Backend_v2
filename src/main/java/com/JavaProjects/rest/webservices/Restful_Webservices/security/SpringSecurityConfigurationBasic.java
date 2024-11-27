package com.JavaProjects.rest.webservices.Restful_Webservices.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

//@Configuration
public class SpringSecurityConfigurationBasic {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager()
    {

        UserDetails userDetails1 = creatNewUser("Jay", "vinayak");
        UserDetails userDetails2 = creatNewUser("Raj", "siva");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails creatNewUser(String username, String password) {
        Function<String,String> passwordEncoder = input -> passwordEncoder().encode(input);
        return User.builder()
                .passwordEncoder(passwordEncoder).username(username)
                .password(password).roles("USER","ADMIN").build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults())

                .sessionManagement((
                session) ->session.sessionCreationPolicy
                (SessionCreationPolicy.STATELESS))

                .csrf(AbstractHttpConfigurer::disable)

                .headers((headers) -> headers.frameOptions
                (HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }
}
