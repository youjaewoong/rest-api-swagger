package com.restapi.exception;

import lombok.Getter;


@Getter
public enum ErrorCode {

   // Common
   INVALID_INPUT_VALUE(400, "C001", "The input value is invalid."),
   INVALID_TYPE_VALUE(404, "C002", " Invalid Type Value."),
   METHOD_NOT_ALLOWED(405, "C003", " The Method is not allowed."),
   HANDLE_ACCESS_DENIED(403, "C004", "Access is Denied."),
   INTERNAL_SERVER_ERROR(500, "C005", "Internal Server Error."),

   // File
   FILE_SIZE_ERROR(500, "F001", "The file size must be less than 20 MB."),
   FILE_TYPE_ERROR(500, "F002", "The file type is supposed to dangerous and malicious."),

   // User
   INVALID_USERNAME(500, "U001", "The ID is duplicated or ID can be used for more than 6 characters and less than 16 characters.");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
    
    
}