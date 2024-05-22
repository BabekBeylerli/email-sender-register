package com.example.firsttask.service.auth;

import com.example.firsttask.dao.entity.UserEntity;
import com.example.firsttask.dao.repository.UserRepository;
import com.example.firsttask.mapper.UserMapper;
import com.example.firsttask.model.auth.AuthRequestDto;
import com.example.firsttask.model.auth.AuthenticationDto;
import com.example.firsttask.model.auth.UserRegisterRequestDto;
import com.example.firsttask.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final MailService mailService;

    public void register(UserRegisterRequestDto requestDto) {
        var userEntity = userMapper.mapRegisterRequestDtoToEntity(requestDto);
        userEntity.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(userEntity);
        mailService.sendRegistrationEmail(userEntity.getEmail());
    }

    public AuthenticationDto authenticate(AuthRequestDto authRequestDto) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getEmail(),
                        authRequestDto.getPassword()
                )
        );
        UserEntity user = userRepository.findUserByEmail(authRequestDto.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationDto.builder()
                .token(jwtToken)
                .build();
    }

    public void deleteUser(Integer userId) {
        log.info("Deleting user with ID: {}", userId);
        userRepository.deleteById(userId);
        log.info("User deleted successfully");
    }

    public static UserEntity getCurrentUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
