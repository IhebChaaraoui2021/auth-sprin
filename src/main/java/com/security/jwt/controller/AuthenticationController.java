package com.security.jwt.controller;

import com.security.jwt.dto.JwtAuthenticationResponse;
import com.security.jwt.dto.LoginRequest;
import com.security.jwt.dto.RegisterRequest;
import com.security.jwt.model.User;
import com.security.jwt.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Async
    public CompletableFuture<ResponseEntity<User>> register(@RequestBody RegisterRequest registerRequest) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(authenticationService.register(registerRequest)));
    }

    @PostMapping("/login")
    @Async
    public CompletableFuture<ResponseEntity<JwtAuthenticationResponse>> login(@RequestBody LoginRequest loginRequest) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(authenticationService.login(loginRequest)));
    }
}
