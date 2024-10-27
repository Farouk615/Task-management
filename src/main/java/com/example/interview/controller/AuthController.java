package com.example.interview.controller;

import com.example.interview.dto.request.LoginRequestDto;
import com.example.interview.dto.request.RegisterUserRequestDto;
import com.example.interview.dto.response.LoginResponseDto;
import com.example.interview.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    @PostMapping("register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) {
        boolean auth = authenticationService.register(registerUserRequestDto);
        if(auth){
            return new ResponseEntity<>("User created", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Error occured", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("login")
        public ResponseEntity<LoginResponseDto> authenticate(@Valid @RequestBody LoginRequestDto loginRequestDto) {
            return new ResponseEntity<>(authenticationService.login(loginRequestDto),HttpStatus.OK);
        }
}
