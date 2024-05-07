package com.restapi.auth.dto;

import com.restapi.auth.constant.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private String username;
    private String password;
    private String nickname;
    private String role;
    public static UserDto createNewUser(PasswordEncoder passwordEncoder, SignupDto signupDto) {
        String encryptedPassword = passwordEncoder.encode(signupDto.getPassword());

        return UserDto.builder()
                .username(signupDto.getUsername())
                .password(encryptedPassword)
                .nickname(signupDto.getNickname())
                .role(Roles.USER)
                .build();
    }
    public boolean isPasswordMatch(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, this.password);
    }

}
