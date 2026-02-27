// package bcc.delivery.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebConfig implements WebMvcConfigurer {   

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**")                          // todos os endpoints
//                 .allowedOrigins("http://localhost:5173")    // porta do seu front-end
//                 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // métodos liberados
//                 .allowCredentials(true);                   // permite cookies / autenticação
//     }
// }