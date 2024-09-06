package com.TintaTotal.Login.SecurityConfig;

import com.TintaTotal.Login.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/login").permitAll()  // Permite el acceso a la página de login
                .requestMatchers("/h2-console/**").permitAll() // Permite el acceso a la consola H2
                 // Asegura que /correct requiere autenticación
                .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                .and()
                .formLogin()
                .loginPage("/login") // Página de login personalizada
                .defaultSuccessUrl("/correct", true) // Redirige a /correct después de un inicio de sesión exitoso
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable() // Desactiva CSRF solo para la consola H2
                .headers().frameOptions().sameOrigin(); // Permite iframe desde la misma fuente
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
