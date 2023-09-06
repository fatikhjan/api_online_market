package com.market.app_online_market.config;

import com.market.app_online_market.Jwt.JWTAuthenticationFilter;
import com.market.app_online_market.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterChainConfig {

    private final AuthenticationProvider authenticationProvider;

    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationEntryPoint authenticationEntryPoint;


    @Bean
    public HttpFirewall allowedEncode() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/admin/**").hasRole(Role.ADMIN.name())
                .requestMatchers("/api/v1/user/**").hasRole(Role.USER.name())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .formLogin()
                .loginPage("/api/v1/auth/*")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        return httpSecurity.build();
    }

}
