package br.cefetrj.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http,
                        CorsConfigurationSource corsConfigurationSource)
                        throws Exception {
                http
                                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                                .csrf(csrf -> csrf.disable()) // IMPORTANTE: Desabilita CSRF para APIs
                                .authorizeHttpRequests(authz -> authz

                                                .requestMatchers("/auth/google/**")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                // SE Não Fosse restful.oauth2Login(oauth -> oauth.loginPage("/login"))

                                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()));
                // .logout(logout -> logout.logoutSuccessUrl("/").permitAll());

                return http.build();
        }

}