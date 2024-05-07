package com.restapi.auth.controller;

import com.restapi.auth.dto.SignupDto;
import com.restapi.auth.dto.UserDto;
import com.restapi.auth.service.SignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignupController {

    private final SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> insertNewUser(@RequestBody SignupDto signupDto) {
        return ResponseEntity.ok().body(signupService.insertNewUser(signupDto));
    }

}
