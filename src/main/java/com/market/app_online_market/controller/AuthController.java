package com.market.app_online_market.controller;

import com.market.app_online_market.payload.AuthenticationRequest;
import com.market.app_online_market.payload.AuthenticationUser;
import com.market.app_online_market.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(BaseController.baseController + AuthController.path)
public class AuthController {
    public static final String path = "/auth";

    private final AuthService authService;


    @PutMapping("/login")
    public ResponseEntity<AuthenticationUser> login(
            @RequestBody String userName,
            String password
    ) {
        AuthenticationUser auth = authService.login(new AuthenticationRequest(userName, password));
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, auth.token())
                .body(auth);
    }


}
