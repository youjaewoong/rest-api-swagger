package com.restapi.auth.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class SignupDto {

    @ApiParam(defaultValue = "aaabbb@google.com")
    private String username;

    @ApiParam(defaultValue = "abcdef1234")
    private String password;

    @ApiParam(defaultValue = "컴공이")
    private String nickname;
}
