package com.example.gestionDeCovoiturage.security;

import com.example.gestionDeCovoiturage.models.Role;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

   private final UserDetailsService userDetailsService;
   private final PasswordEncoder passwordEncoder;


   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
              .csrf().disable()
              .authorizeHttpRequests()
              .requestMatchers("/api/auth/**" ,"/auth/**", "/assets/**")
              .permitAll()
              .requestMatchers("/client/**").hasAnyAuthority("CLIENT", "ADMIN")
              .requestMatchers("/admin/**","/api/admin/**").hasAuthority("ADMIN")
              .anyRequest()
              .authenticated()
              .and()
              .formLogin()
              .loginPage("/auth/login")
              .failureForwardUrl("/auth/login-error")
              .and()
              .exceptionHandling(config -> {
                 config.accessDeniedHandler(((request, response, accessDeniedException) -> {
                    System.out.println("access denied exception " + accessDeniedException.getMessage());
                 }));
              });
      return http.build();
   }


   @Bean
   public AuthenticationProvider authenticationProvider() {
      final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setUserDetailsService(userDetailsService);
      provider.setPasswordEncoder(passwordEncoder);
      return provider;
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
      return config.getAuthenticationManager();
   }

}
