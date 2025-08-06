package com.multiserviceplatform.security;

import com.multiserviceplatform.model.Admin;
import com.multiserviceplatform.model.User;
import com.multiserviceplatform.repository.AdminRepository;
import com.multiserviceplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.LocalDate;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   @Autowired
   private AdminRepository adminRepository;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/**").hasAnyRole("ADMIN", "SEEKER", "PROVIDER")
                        .requestMatchers("/services/**").hasAnyRole("ADMIN", "PROVIDER")
                        //.requestMatchers("/service-providers/**").hasAnyRole("ADMIN", "PROVIDER")
                        .requestMatchers("/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
//                .headers(headers ->
//                    headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        //.requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CommandLineRunner initData(UserRepository userRepository) {
        return args -> {

            if(! userRepository.existsByEmail("seeker1@example.com")){
                User user = new User("seeker1@example.com", passwordEncoder().encode("pass1"),
                        "09998887700", "Seeker");
                userRepository.save(user);
            }

            if(! userRepository.existsByEmail("seeker2@example.com")){
                User user = new User("seeker2@example.com", passwordEncoder().encode("pass2"),
                        "09994447700", "Seeker");
                userRepository.save(user);
            }

            if(! userRepository.existsByEmail("provider1@example.com")){
                User user = new User("provider1@example.com", passwordEncoder().encode("provider1"),
                        "09995557700", "Provider");
                userRepository.save(user);
            }

            if(! userRepository.existsByEmail("admin1@example.com")){
                User user = new User("admin1@example.com", passwordEncoder().encode("admin1"),
                        "09990007700", "Admin");
                userRepository.save(user);
                Admin newAdmin;
                if( !adminRepository.existsByUser(user) ){
                    Admin admin = new Admin();
                    admin.setUser(user);
                    admin.setAccessLevel("Super Admin");
                    newAdmin = adminRepository.save(admin);
                    user.setAdmin(newAdmin);
                }
            }
        };
    }
}
