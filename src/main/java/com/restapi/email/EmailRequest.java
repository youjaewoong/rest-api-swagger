package com.restapi.email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "이메일 기본 데이터", description = "제목, 이메일 Domain Class")
public class EmailRequest implements EmailSwaggerMessage {
	
	@NotBlank(message = SUBJECT_MESSAGE)
	@ApiModelProperty(value=SUBJECT_VALUE, example = SUBJECT_EXAMPLE)
	private String subject;
	
	@Email
	@NotBlank
	@ApiModelProperty(required = true, value=EMAIL_VALUE, example = EMAIL_EXAMPLE)
	private String email;
	
	@ApiModelProperty(hidden = true)
	private String contetns;
}
