package com.foodexpress.accountservice.adapter.out.jwt;

import com.foodexpress.accountservice.application.port.in.GetAccountUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final Jwt jwt;

    private final JwtProperties jwtProperties;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final EntryPointHandler unAuthorizedHandler;

    private final GetAccountUseCase getAccountUseCase;

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter(jwtProperties.getHeader(), jwt);
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(getAccountUseCase);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .exceptionHandling(handle -> handle.accessDeniedHandler(jwtAccessDeniedHandler).authenticationEntryPoint(unAuthorizedHandler)).headers(
                config -> config.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)).sessionManagement(session -> session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS)).authorizeHttpRequests(request -> request.requestMatchers("/swagger-ui/**",
                                                                                                           "/v3/api-docs/**",
                                                                                                           "/swagger-resources/**",
                                                                                                           "/account/auth/**",
                                                                                                           "/actuator/**").permitAll()
                .anyRequest().authenticated()).addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
