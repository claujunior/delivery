package bcc.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")                        
                        .allowedOrigins("http://localhost:5173") 
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true);                
            }
        };
    }

    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)           
            .cors(Customizer.withDefaults())                
            .headers(headers -> headers.frameOptions(frame -> frame.disable())) 
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
              
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/foods/get").permitAll()

                
                .requestMatchers(HttpMethod.POST, "/foods/save").permitAll()

              
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .httpBasic(AbstractHttpConfigurer::disable); 

        return http.build();
    }

   
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder enc = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user = User.builder()
                .username("usuario")
                .password(enc.encode("123"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(enc.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}