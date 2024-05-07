package com.restapi.auth.controller;

import com.restapi.auth.dto.UserDto;
import com.restapi.auth.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalTime;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final RedisTemplate<String, String> redisTemplate;

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

    @GetMapping("/login-check")
    public ResponseEntity<String> loginCheck(HttpSession session) {
        log.info("session id: {}", session.getId());
        String result;
        Object maybeUser = session.getAttribute("userInfo");
        if (ObjectUtils.isEmpty(maybeUser)) {
            result = "로그인 되지 않음";
        } else {
            result = "로그인";
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/login-time-check")
    public String loginTimeCheck(HttpSession session) {
        log.info("session id: {}", session.getId());
        String sessionKey = "spring:session:sessions:" + session.getId();  // 세션 키 포맷에 주의하세요.
        Long ttl = redisTemplate.getExpire(sessionKey);

        if (ttl != null && ttl > 0) {
            Duration duration = Duration.ofSeconds(ttl);
            LocalTime time = LocalTime.ofSecondOfDay(duration.getSeconds());
            return time.toString();  // 표준 시간 형식으로 변환 (HH:mm:ss)
        } else {
            return "Session expired or does not exist";  // 만료 또는 존재하지 않는 세션
        }
    }


    @PostMapping("/session/logout")
    public ResponseEntity<?> sessionInfo(HttpSession session) {
        log.info("session id: {}", session.getId());
        session.invalidate();
        return ResponseEntity.ok().body(session.getId());
    }
}
