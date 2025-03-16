package org.example.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
//    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/rest-api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/categories").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

//        http
//                .cors().and().csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/account/**").permitAll()
//                .requestMatchers("/files/**").permitAll()
//                .requestMatchers("/static/**").permitAll()
//                .requestMatchers("/swagger-resources/**").permitAll()
//                .requestMatchers("/v3/api-docs/**").permitAll()
//                .requestMatchers("/webjars/**").permitAll()
//                .requestMatchers("/rest-api-docs/**").permitAll()
//                .requestMatchers("/swagger-ui/**").permitAll()
//                .requestMatchers(HttpMethod.GET,"/api/categories").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.POST,"/api/categories").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.GET,"/api/products").permitAll()
//                .requestMatchers("/api/products/**").hasAuthority("ADMIN")
//                .anyRequest().authenticated()
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
////                .logout()
////                .logoutUrl("/api/v1/auth/logout")
////                .addLogoutHandler(logoutHandler)
////                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//        ;

//        return http.build();
    }


}

