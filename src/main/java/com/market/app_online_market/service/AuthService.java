package com.market.app_online_market.service;

import com.market.app_online_market.Jwt.JWTUtil;
import com.market.app_online_market.domain.User;
import com.market.app_online_market.payload.AuthenticationRequest;
import com.market.app_online_market.payload.AuthenticationUser;
import com.market.app_online_market.repositores.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepository;

    private final AuthenticationManager authenticationManager;

    private final JWTUtil util;

    public AuthenticationUser login(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );
        User princpal = (User) authentication.getPrincipal();
        userRepository.save(princpal);
        String token = util.issueToken(princpal.getUsername(),
                princpal.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()));

        return new AuthenticationUser(token, princpal);
    }
}
