package com.quizz.app.config;

import com.quizz.app.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    @Autowired
    private AuthenticationService authenticationService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers("/signup","/signup/**","/css/**","/js/**","/login/**","/static/**","/img/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/", true)
                .and()
                    .logout()
                    .logoutSuccessUrl("/login")
                .and()
                    .headers()
                    .frameOptions()
                    .disable()
                .and()
                .authenticationManager(new ProviderManager(authenticationService))
        ;

        return http.build();
    }
}
