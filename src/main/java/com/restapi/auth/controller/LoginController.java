package com.restapi.auth.controller;

import com.restapi.auth.dto.UserDto;
import com.restapi.auth.dto.UserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<UserInfoDto> login(HttpSession session, @RequestBody UserDto dto) {
        log.info("session type: {}", session.getClass().getName());
        log.info("session id: {}", session.getId());

        UserInfoDto userInfo = new UserInfoDto();
        userInfo.setSessionId("hgetall spring:session:sessions:"+session.getId());
        userInfo.setToken(session.getId());
        userInfo.setUser(dto);

        session.setAttribute("userInfo", userInfo);
        return ResponseEntity.ok().body(userInfo);
    }


    @PostMapping("/session/logout")
    public ResponseEntity<?> sessionInfo(HttpSession session) {
        log.info("session id: {}", session.getId());
        session.invalidate();
        return new ResponseEntity<>("로그아웃 성공 :" + session.getId(), HttpStatus.OK);
    }
}
