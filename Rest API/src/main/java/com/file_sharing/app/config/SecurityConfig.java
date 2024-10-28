package com.file_sharing.app.config;

<<<<<<< HEAD
import java.util.List;

=======
import com.file_sharing.app.helper.AppCon;
import com.file_sharing.app.security.JWTAuthenticationEntryPoint;
import com.file_sharing.app.security.JWTAuthenticationFilter;
import jakarta.servlet.http.HttpSession;
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

<<<<<<< HEAD
import com.file_sharing.app.helper.AppCon;
import com.file_sharing.app.security.JWTAuthenticationEntryPoint;
import com.file_sharing.app.security.JWTAuthenticationFilter;

import jakarta.servlet.http.HttpSession;

@Configuration
public class SecurityConfig {

    private JWTAuthenticationFilter jwtAuthenticationFilter;
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

=======
import java.util.List;

@Configuration
public class SecurityConfig {
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
    public SecurityConfig(JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint, JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }
<<<<<<< HEAD

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSession httpSession) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable); // Disables CSRF as JWT is used for stateless authentication.

        // Configures CORS settings
        http.cors(httpSecurityCorsConfigurer
                -> httpSecurityCorsConfigurer.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOriginPatterns(List.of("*")); // Allows any origin (for development).
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed HTTP methods.
                    config.setAllowCredentials(true); // Allows credentials to be included in requests.
                    config.setAllowedHeaders(List.of("*")); // Allows all headers.
                    config.setMaxAge(3600L); // Cache duration of CORS config (in seconds).
                    return config;
                })
        );

        // Configures authorization rules for specific endpoints
        http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests
                    .requestMatchers(HttpMethod.POST, "/file/**").hasAnyRole(AppCon.ROLE_NORMAL, AppCon.ROLE_ADMIN)
                    .requestMatchers(HttpMethod.PUT, "/file/**", "/user/**").hasAnyRole(AppCon.ROLE_NORMAL, AppCon.ROLE_ADMIN)
                    .requestMatchers(HttpMethod.DELETE, "/file/**", "/user/**").hasAnyRole(AppCon.ROLE_NORMAL, AppCon.ROLE_ADMIN)
                    .anyRequest().permitAll(); // Permits all other requests.
        });

        // Configures exception handling using a custom authentication entry point for unauthorized access
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));

        // Sets session policy to stateless, as JWT-based authentication doesn't require server-side sessions
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Adds JWT filter to process JWT tokens before UsernamePasswordAuthenticationFilter
=======
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSession httpSession) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        // cors Configuration
        http.cors(httpSecurityCorsConfigurer ->
                httpSecurityCorsConfigurer.configurationSource(request ->{
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOriginPatterns(List.of("*"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(List.of("*"));
                    config.setMaxAge(3600L);
                    return config;
                })
        );
        // Security Configuration
        http.authorizeHttpRequests(
                // URL Configuration
                authorizeRequests ->{
                    authorizeRequests
                            .requestMatchers(HttpMethod.POST, "/file/**").hasAnyRole(AppCon.ROLE_NORMAL, AppCon.ROLE_ADMIN)
                            .requestMatchers(HttpMethod.PUT, "/file/**", "/user/**").hasAnyRole(AppCon.ROLE_NORMAL, AppCon.ROLE_ADMIN)
                            .requestMatchers(HttpMethod.DELETE, "/file/**", "/user/**").hasAnyRole(AppCon.ROLE_NORMAL, AppCon.ROLE_ADMIN)
                            .anyRequest().permitAll();
                });
        // for any exception
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));
        // Stateless Session Creation Policy set
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
<<<<<<< HEAD

    // Configures PasswordEncoder as BCryptPasswordEncoder
=======
    //Password Encoder bean
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
<<<<<<< HEAD

    // Configures AuthenticationManager to be used for authentication
=======
    //Authentication Manager
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
