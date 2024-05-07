package com.restapi.login.controller;

import com.restapi.login.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class LoginController {

    @PostMapping("/session/login")
    public ResponseEntity<UserDto> login(HttpSession session, @RequestBody UserDto dto) {
        log.info("session type: {}", session.getClass().getName());
        log.info("session id: {}", session.getId());
        dto.setSessionId("hgetall spring:session:sessions:"+session.getId());
        session.setAttribute("userInfo", dto);
        return ResponseEntity.ok().body(dto);
    }


    @PostMapping("/session/logout")
    public ResponseEntity<?> sessionInfo(HttpSession session) {
        log.info("session id: {}", session.getId());
        session.invalidate();
        return new ResponseEntity<>("로그아웃 성공 :" + session.getId(), HttpStatus.OK);
    }
}
