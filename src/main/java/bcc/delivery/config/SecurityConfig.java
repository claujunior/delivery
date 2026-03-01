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

    // 1️⃣ Configuração global de CORS
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")                        // todos os endpoints
                        .allowedOrigins("http://localhost:5173") // front-end
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true);                 // permite cookies/autenticação
            }
        };
    }

    // 2️⃣ Configuração do Spring Security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)           // desativa CSRF para teste
            .cors(Customizer.withDefaults())                 // habilita CORS
            .headers(headers -> headers.frameOptions(frame -> frame.disable())) // H2 console
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // H2 console liberado
                .requestMatchers("/h2-console/**").permitAll()

                // GET liberado para todos
                .requestMatchers(HttpMethod.GET, "/foods/get").permitAll()

                // POST só para ADMIN
                .requestMatchers(HttpMethod.POST, "/foods/save").permitAll()

                // qualquer outra requisição precisa de autenticação
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())           // login padrão do Spring
            .httpBasic(Customizer.withDefaults());          // habilita HTTP Basic Auth

        return http.build();
    }

    // 3️⃣ Usuários em memória (USER e ADMIN)
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