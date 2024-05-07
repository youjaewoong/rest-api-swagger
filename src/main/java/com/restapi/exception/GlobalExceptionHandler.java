package com.restapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
    * @Valid binding error가 발생할 때 발생
    */
   @ExceptionHandler(MethodArgumentNotValidException.class)
   protected ResponseEntity<ErrorResponse2> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
       log.error("handleMethodArgumentNotValidException", e);
       final ErrorResponse2 response = ErrorResponse2.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());

       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
   }

   /**
    * @ModelAttribute bindingResult error가 발생할 때 발생
    */
   @ExceptionHandler(BindException.class)
   protected ResponseEntity<ErrorResponse2> handleBindException(BindException e) {
       log.error("handleBindException", e);
       final ErrorResponse2 response = ErrorResponse2.of(ErrorCode.INVALID_TYPE_VALUE, e.getBindingResult());

       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
   }

   /**
    * enum type binding error가 발생할 때 발생
    */
   @ExceptionHandler(MethodArgumentTypeMismatchException.class)
   protected ResponseEntity<ErrorResponse2> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
       log.error("handleMethodArgumentTypeMismatchException", e);
       final ErrorResponse2 response = ErrorResponse2.of(e);

       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
   }

   /**
    * 지원하지 않은 HTTP method를 호출 할 때 발생
    */
   @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
   protected ResponseEntity<ErrorResponse2> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
       log.error("handleHttpRequestMethodNotSupportedException", e);
       final ErrorResponse2 response = ErrorResponse2.of(ErrorCode.METHOD_NOT_ALLOWED);

       return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
   }

   /**
    * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생
    */
   @ExceptionHandler(AccessDeniedException.class)
   protected ResponseEntity<ErrorResponse2> handleAccessDeniedException(AccessDeniedException e) {
       log.error("handleAccessDeniedException", e);
       final ErrorResponse2 response = ErrorResponse2.of(ErrorCode.HANDLE_ACCESS_DENIED);

       return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.HANDLE_ACCESS_DENIED.getStatus()));
   }

   /**
    * multipart에서 설정한 file size보다 큰 파일이 업로드 되는 경우 발생
    */
   @ExceptionHandler(MaxUploadSizeExceededException.class)
   protected ResponseEntity<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
       log.error("handleMaxUploadSizeExceededException", e);
       final ErrorResponse2 response = ErrorResponse2.of(ErrorCode.FILE_SIZE_ERROR);

       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
   }

   /**
    * file type이 위험하고 악의적인 것으로 판별되는 경우 발생
    */
   @ExceptionHandler(FileTypeException.class)
   protected ResponseEntity<?> handleFileTypeException(Exception e) {
       log.error("handleFileTypeException", e);
       final ErrorResponse2 response = ErrorResponse2.of(ErrorCode.FILE_TYPE_ERROR, e.getMessage());

       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
   }

   /**
    * 비즈니스 요구사항에 따른 Exception
    * 비즈니스 요구사항에 예외일 경우 BusinessException으로 통일성 있게 처리
    * 추가로 늘어날 수는 있지만 exception 개수를 최소화 해야함
    */
   @ExceptionHandler(BusinessException.class)
   protected ResponseEntity<ErrorResponse2> handleBusinessException(final BusinessException e) {
       log.error("handleBusinessException", e);
       final ErrorCode errorCode = e.getErrorCode();
       final ErrorResponse2 response = ErrorResponse2.of(errorCode);

       return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
   }

   /**
    * 그 밖에 발생하는 모든 예외 처리, Null Point Exception 등
    * 개발자가 직접 핸들링해서 다른 예외로 던지지 않으면 발생
    */
   @ExceptionHandler(Exception.class)
   protected ResponseEntity<ErrorResponse2> handleException(Exception e) {
       log.error("handleException", e);
       final ErrorResponse2 response = ErrorResponse2.of(ErrorCode.INTERNAL_SERVER_ERROR);

       return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
   }
}