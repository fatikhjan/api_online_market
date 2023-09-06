package com.market.app_online_market.controller;

import com.market.app_online_market.payload.AuthLoginDTO;
import com.market.app_online_market.payload.AuthenticationUser;
import com.market.app_online_market.payload.RegoisterDTO;
import com.market.app_online_market.payload.RespAPI;
import com.market.app_online_market.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BaseController.baseController + AuthController.path)
public class AuthController {
    public static final String path = "/auth";

    private final AuthService authService;


    @PutMapping("/login")
    public ResponseEntity<AuthenticationUser> login(
            @RequestBody AuthLoginDTO authLoginDTO
    ) {
        AuthenticationUser auth = authService.login(authLoginDTO);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, auth.token())
                .body(auth);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegoisterDTO regoisterDTO
    ) {
        RespAPI res = authService.register(regoisterDTO);
        return ResponseEntity.status(res.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(res);
    }

}
