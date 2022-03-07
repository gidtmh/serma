package com.example.server.config;

import com.example.server.auth.JwtTokenFilter;
import com.example.server.exception.ExceptionHandlerFilter;
import com.example.server.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenUtil jwtTokenUtil;
    private static final String[] AUTH_ENDPOINTS = new String[]{"/api/auth/login",
        "/api/auth/register"};

    private static final String REFRESH_TOKENS_ENDPOINT = "/api/auth/refresh-tokens";
    private static final String EVENTS_ENDPOINTS = "/api/events/**";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenFilter jwtAuthenticationFilter() {
        return new JwtTokenFilter(jwtTokenUtil);
    }

    @Bean
    public ExceptionHandlerFilter exceptionHandlerFilter() {
        return new ExceptionHandlerFilter();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .disable()
            .csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(AUTH_ENDPOINTS)
            .permitAll()
            .antMatchers(REFRESH_TOKENS_ENDPOINT, EVENTS_ENDPOINTS)
            .authenticated()
            .anyRequest()
            .permitAll();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(exceptionHandlerFilter(), CorsFilter.class);
    }
}
