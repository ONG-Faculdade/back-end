package br.com.adocao.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final SecurityFilter securityFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService, SecurityFilter securityFilter) {
        this.userDetailsService = userDetailsService;
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Permissions for authentication (login and registration)
                        .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()

                        // Animal-related routes
                        .requestMatchers(HttpMethod.POST, "/animal/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/animal").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/animal/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/animal/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/animal/adopt/{id}").hasRole("USER")

                        // User routes
                        .requestMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/{email}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/user/permission/{email}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/update/{email}").hasRole("USER")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Using BCrypt for password encoding
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
