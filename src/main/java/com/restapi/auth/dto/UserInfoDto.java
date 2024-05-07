package com.restapi.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserInfoDto {

    private UserDto user;

    private String token;

    @JsonIgnore
    private String sessionId;
}
