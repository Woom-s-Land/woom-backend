package com.ee06.wooms.global.security;

import com.ee06.wooms.domain.users.service.CustomOAuth2UserService;
import com.ee06.wooms.global.jwt.JWTUtil;
import com.ee06.wooms.global.jwt.filter.JWTFilter;
import com.ee06.wooms.global.jwt.filter.LoginFilter;
import com.ee06.wooms.global.oauth.CustomSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JWTUtil jwtUtil;

    private final CustomSuccessHandler customSuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequest) -> authorizeRequest
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers("/", "api/auth/**").permitAll()
                        .requestMatchers("/api/users/**").authenticated()
                        .requestMatchers("/api/groups/**").authenticated()
                        .requestMatchers("/api/letters/**").authenticated()
                        .requestMatchers("/api/comments/**").authenticated()
                        .anyRequest().permitAll()
                )
                .headers((headersConfigurer) ->
                        headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler)
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .with(new Custom(authenticationConfiguration, jwtUtil), Custom::getClass);

        return http.build();
    }

    @RequiredArgsConstructor
    public static class Custom extends AbstractHttpConfigurer<Custom, HttpSecurity> {
        private final AuthenticationConfiguration authenticationConfiguration;
        private final JWTUtil jwtUtil;

        @Override
        public void configure(HttpSecurity http) throws Exception {
            LoginFilter loginFilter = new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil);
            loginFilter.setFilterProcessesUrl("/api/auth");

            http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);
            http.addFilterAfter(new JWTFilter(jwtUtil, "OAUTH"), OAuth2LoginAuthenticationFilter.class);
            http.addFilterBefore(new JWTFilter(jwtUtil, "COMMON"), LoginFilter.class);
        }
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
