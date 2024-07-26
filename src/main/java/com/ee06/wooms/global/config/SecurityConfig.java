package com.ee06.wooms.global.config;

import com.ee06.wooms.domain.users.service.CustomOAuth2UserService;
import com.ee06.wooms.global.jwt.JWTUtil;
import com.ee06.wooms.global.jwt.filter.CustomLogoutFilter;
import com.ee06.wooms.global.jwt.filter.JWTFilter;
import com.ee06.wooms.global.jwt.filter.LoginFilter;
import com.ee06.wooms.global.jwt.repository.RefreshTokenRepository;
import com.ee06.wooms.global.oauth.CustomSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JWTUtil jwtUtil;

    private final CustomSuccessHandler customSuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    private final AuthenticationConfiguration authenticationConfiguration;
    private final RefreshTokenRepository refreshTokenRepository;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**")
                .requestMatchers(PathRequest.toH2Console());
    }

    private static final String[] AUTH_WHITELIST = {
            "/re",
            "/api/auth/**",
            "/api/users/**",
            "/api/groups/**",
            "/api/letters/**",
            "/api/comments/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequest) -> authorizeRequest
                        .requestMatchers(AUTH_WHITELIST).permitAll()
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
                .exceptionHandling((handler) -> handler
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .with(new Custom(
                        authenticationConfiguration,
                        refreshTokenRepository,
                        jwtUtil), Custom::getClass
                );

        return http.build();
    }

    @RequiredArgsConstructor
    public static class Custom extends AbstractHttpConfigurer<Custom, HttpSecurity> {
        private final AuthenticationConfiguration authenticationConfiguration;
        private final RefreshTokenRepository refreshTokenRepository;
        private final JWTUtil jwtUtil;

        @Override
        public void configure(HttpSecurity http) throws Exception {
            LoginFilter loginFilter = new LoginFilter(authenticationManager(authenticationConfiguration), refreshTokenRepository, jwtUtil);
            loginFilter.setFilterProcessesUrl("/api/auth");
            loginFilter.setPostOnly(true);

            JWTFilter oauthJwtFilter = new JWTFilter(jwtUtil, "OAUTH");
            JWTFilter commonJwtFilter = new JWTFilter(jwtUtil, "COMMON");

            http
                    .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                    .addFilterAfter(oauthJwtFilter, OAuth2LoginAuthenticationFilter.class)
                    .addFilterBefore(commonJwtFilter, OAuth2LoginAuthenticationFilter.class)
                    .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshTokenRepository), LogoutFilter.class);
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
