package com.restapi.helloworld;
// lombok

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor //매개변수가없는 생성자
public class HelloWorldBean {
    private String message;
    
}
