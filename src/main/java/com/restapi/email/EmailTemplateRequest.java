package com.restapi.email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "이메일 템플릿 정보", description = "이름, 나이, 사업자번호, 다국어 Domain Class")
@Data
public class EmailTemplateRequest extends EmailRequest{
	
	@NotBlank(message = NAME_MESSAGE)
	@ApiModelProperty(required = true, value=NAME_VALUE, example = NAME_EXAMPLE)
	private String name;
	
	@NotBlank(message = AGE_MESSAGE)
	@ApiModelProperty(required = true, value=AGE_VALUE, example = AGE_EXAMPLE)
	private String age;
	
	@NotBlank(message = BIZNO_MESSAGE)
	@ApiModelProperty(required = true, value=BIZNO_VALUE, example = BIZNO_EXAMPLE)
	private String bizNo;
	
	@ApiModelProperty(value=MULTILINGUAL_VALUE, allowableValues = MULTILINGUAL_EXAMPLE)
	private String multilingual;
}
