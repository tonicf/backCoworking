package com.aytocarmona.coworking.v1.security.config;


import com.aytocarmona.coworking.v1.security.authentication.JWTAuthenticationFilter;
import com.aytocarmona.coworking.v1.security.authorization.JWTAuthorizationFilter;
import com.aytocarmona.coworking.v1.security.constants.SecurityConstants;
import com.aytocarmona.coworking.v1.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * This class configures the web security settings for the application.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * An instance of UserDetailsServiceImpl.
     */
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * An instance of JWTAuthorizationFilter.
     */
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    /**
     * Constructor for WebSecurityConfig.
     *
     * @param userDetailsService     The UserDetailsServiceImpl implementation.
     * @param jwtAuthorizationFilter The JWTAuthorizationFilter implementation.
     */
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    /**
     * Configures the security filter chain.
     *
     * @param http                  The HttpSecurity object.
     * @param authenticationManager The AuthenticationManager object.
     * @return the configured SecurityFilterChain.
     * @throws Exception if an exception occurs during configuration.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);

        return http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, JWTAuthenticationFilter.class)
                .build();
    }

    /**
     * Configures the AuthenticationManager.
     *
     * @param http The HttpSecurity object.
     * @return the configured AuthenticationManager.
     * @throws Exception if an exception occurs during configuration.
     */
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    /**
     * Configures the PasswordEncoder.
     *
     * @return the configured PasswordEncoder.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates a CorsConfigurationSource instance with CORS configuration.
     *
     * @return CorsConfigurationSource instance.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(SecurityConstants.ANGULAR_URL);
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(SecurityConstants.API_URL, configuration);
        return source;
    }
}

