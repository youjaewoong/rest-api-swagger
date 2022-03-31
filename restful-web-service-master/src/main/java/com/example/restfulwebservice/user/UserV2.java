package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfoV2")
@Getter
@Setter
public class UserV2 extends User {
    private String grade;
}
