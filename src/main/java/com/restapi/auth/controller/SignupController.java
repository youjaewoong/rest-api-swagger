package com.restapi.auth.controller;

import com.restapi.auth.dto.SignupDto;
import com.restapi.auth.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class SignupController {

    @PostMapping("/signup")
    public ResponseEntity<SignupDto> login(@RequestBody SignupDto signupDto) {

        log.info("signupDto {}", signupDto);
        return ResponseEntity.ok().body(signupDto);
    }

}
