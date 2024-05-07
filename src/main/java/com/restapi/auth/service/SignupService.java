package com.restapi.auth.service;

import com.restapi.auth.dto.SignupDto;
import com.restapi.auth.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignupService {

    private final PasswordEncoder passwordEncoder;

    public UserDto insertNewUser(SignupDto signupDto) {
        return UserDto.createNewUser(passwordEncoder, signupDto);
    }

}
