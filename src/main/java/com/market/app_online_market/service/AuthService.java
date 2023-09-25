package com.market.app_online_market.service;

import com.market.app_online_market.Jwt.JWTUtil;
import com.market.app_online_market.domain.Users;
import com.market.app_online_market.domain.enums.Role;
import com.market.app_online_market.payload.AuthLoginDTO;
import com.market.app_online_market.payload.AuthenticationUser;
import com.market.app_online_market.payload.RegoisterDTO;
import com.market.app_online_market.payload.RespAPI;
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

    public AuthenticationUser login(AuthLoginDTO authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.userName(),
                        authenticationRequest.password()
                )
        );
        Users princpal = (Users) authentication.getPrincipal();
        userRepository.save(princpal);
        String token = util.issueToken(princpal.getUsername(),
                princpal.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()));

        return new AuthenticationUser(token, princpal);
    }

    public RespAPI<Boolean> register(RegoisterDTO regoisterDTO) {
        if (
                userRepository.findByUser_name(regoisterDTO.userName()).isEmpty()
        ) {
            return new RespAPI<>("User alreay exist", false, false, 409);
        }
        userRepository.save(
                Users.builder()
                        .user_name(regoisterDTO.userName())
                        .email(regoisterDTO.email())
                        .fullName(regoisterDTO.fullName())
                        .number(regoisterDTO.phoneNumber())
                        .password(regoisterDTO.password())
                        .role(Role.USER)
                        .build()
        );

        return new RespAPI<>(
                "User created sucessfuly",
                null,
                true,
                201
        );
    }
}
