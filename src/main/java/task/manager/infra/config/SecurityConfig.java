package task.manager.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Novo estilo funcional para desativar o CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/v3/api-docs/**",         // Endpoints do OpenAPI
                        "/swagger-ui/**",          // Recursos do Swagger UI
                        "/swagger-ui.html",
                        "/api/v1/tasks",
                        "/api/v1/tasks/{id}"// Página principal do Swagger
                ).permitAll() // Permitir acesso público aos endpoints listados
                // .anyRequest().authenticated() // Requer autenticação para outros endpoints
            )
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable); // Configuração de autenticação básica (opcional)

        return http.build();
    }
}