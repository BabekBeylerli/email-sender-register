package com.example.firsttask.controller.auth;

import com.example.firsttask.model.auth.AuthRequestDto;
import com.example.firsttask.model.auth.AuthenticationDto;
import com.example.firsttask.model.auth.UserRegisterRequestDto;
import com.example.firsttask.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(
            @RequestBody UserRegisterRequestDto requestDto
    ) {
        authService.register(requestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDto> login(
            @RequestBody AuthRequestDto authRequestDto
    ) {
        return ResponseEntity.ok(authService.authenticate(authRequestDto));
    }

    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void deleteUser(@PathVariable Integer userId) {
        authService.deleteUser(userId);
    }
}

