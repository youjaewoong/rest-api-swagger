package com.restapi.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse2 {

	   private String message;
	   private String code;
	   private List<FieldError> errors;

	   private ErrorResponse2(final ErrorCode code, final List<FieldError> errors) {
	       this.message = code.getMessage();
	       this.code = code.getCode();
	       this.errors = errors;
	   }

	   private ErrorResponse2(final ErrorCode code) {
	       this.message = code.getMessage();
	       this.code = code.getCode();
	       this.errors = new ArrayList<>();
	   }

	   private ErrorResponse2(final ErrorCode code, final String message) {
	       this.message = message;
	       this.code = code.getCode();
	       this.errors = new ArrayList<>();
	   }

	   public static ErrorResponse2 of(final ErrorCode code, final BindingResult bindingResult) {
	       return new ErrorResponse2(code, FieldError.of(bindingResult));
	   }

	   public static ErrorResponse2 of(final ErrorCode code) {
	       return new ErrorResponse2(code);
	   }

	   public static ErrorResponse2 of(final ErrorCode code, String message) {
	       return new ErrorResponse2(code, message);
	   }

	   public static ErrorResponse2 of(final ErrorCode code, final List<FieldError> errors) {
	       return new ErrorResponse2(code, errors);
	   }

	   public static ErrorResponse2 of(MethodArgumentTypeMismatchException e) {
	       final String value = e.getValue() == null ? "" : e.getValue().toString();
	       final List<FieldError> errors = FieldError.of(e.getName(), value, e.getErrorCode());
	       return new ErrorResponse2(ErrorCode.INVALID_TYPE_VALUE, errors);
	   }

	   @Getter
	   @NoArgsConstructor(access = AccessLevel.PROTECTED)
	   public static class FieldError {
	       private String field;
	       private String value;
	       private String reason;

	       private FieldError(final String field, final String value, final String reason) {
	           this.field = field;
	           this.value = value;
	           this.reason = reason;
	       }

	       public static List<FieldError> of(final String field, final String value, final String reason) {
	           List<FieldError> fieldErrors = new ArrayList<>();
	           fieldErrors.add(new FieldError(field, value, reason));
	           return fieldErrors;
	       }

	       private static List<FieldError> of(final BindingResult bindingResult) {
	           final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
	           return fieldErrors.stream()
	                   .map(error -> new FieldError(
	                           error.getField(),
	                           error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
	                           error.getDefaultMessage()))
	                   .collect(Collectors.toList());
	       }
	   }


}