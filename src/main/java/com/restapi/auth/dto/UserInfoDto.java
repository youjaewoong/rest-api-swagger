package com.restapi.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserInfoDto {

    private UserDto user;

    private String token;

    @JsonIgnore
    private String sessionId;
}
