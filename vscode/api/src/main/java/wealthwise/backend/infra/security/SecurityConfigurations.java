package wealthwise.backend.infra.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors().and() // Aplica configuração de CORS
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        .requestMatchers(HttpMethod.GET, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users/{id}").permitAll()

                        .requestMatchers(HttpMethod.POST, "/wallets").permitAll()
                        .requestMatchers(HttpMethod.GET, "/wallets/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/wallets/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/wallets/{id}").permitAll()
                        
                        .requestMatchers(HttpMethod.POST, "/incomes/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/incomes/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/incomes/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/incomes/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/quotes/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/quotes/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/quotes/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/notifications/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/notifications/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/notifications/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/notifications/**").permitAll()

                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        // .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

