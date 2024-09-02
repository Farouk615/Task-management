package com.example.interview.service;

import com.example.interview.config.filters.JwtService;
import com.example.interview.dto.request.LoginRequestDto;
import com.example.interview.dto.request.RegisterUserRequestDto;
import com.example.interview.dto.response.LoginResponseDto;
import com.example.interview.entity.User;
import com.example.interview.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager ;
    private final JwtService jwtService;


    public Boolean register(RegisterUserRequestDto registerUserRequestDto) {
        if(userRepository.findByEmail(registerUserRequestDto.getEmail()).isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "User Already exist");
        }
        var user = User
                .builder()
                .name(registerUserRequestDto.getName())
                .email(registerUserRequestDto.getEmail())
                .phoneNumber(registerUserRequestDto.getPhoneNumber())
                .password(passwordEncoder.encode(registerUserRequestDto.getPassword()))
                .role(registerUserRequestDto.getRole())
                .build();
        userRepository.save(user);
        return true;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        Optional<User> userOptional = userRepository.findByEmail(loginRequestDto.getEmail());
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Bad credentials");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(userOptional.get());
        return LoginResponseDto.builder().token(jwtToken).build();
    }
}
