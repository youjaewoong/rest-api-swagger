package com.example.restfulwebservice.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ApiV2Reuqest {
	@JsonProperty("ID")
	@ApiModelProperty(example = "123")
    private Long id;
}
