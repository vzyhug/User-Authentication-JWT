package com.example.day1_connectdb.exception;


import com.example.day1_connectdb.dto.resonse.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<APIResponse> handleRuntimeException(RuntimeException exception) {
        APIResponse response = new APIResponse();
        response.setSuccess(1000);
        response.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<APIResponse> handleAppException(AppException exception) {
        APIErrorCode errorCode = exception.getErrorCode();
        APIResponse response = new APIResponse();
        response.setSuccess(errorCode.getSuccess());
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    // Handle validation errors : username , password ...
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<APIResponse> handlingValidation (MethodArgumentNotValidException exception){
        String errorKey = exception.getFieldError().getDefaultMessage();

        APIErrorCode errorCode = APIErrorCode.KEY_INVALID;
        try{
            errorCode = APIErrorCode.valueOf(errorKey);
        }catch (Exception e){
            e.getMessage();
        }
        APIResponse response = new APIResponse();
        response.setSuccess(APIErrorCode.valueOf(errorKey).getSuccess());
        response.setMessage(APIErrorCode.valueOf(errorKey).getMessage());
        return ResponseEntity.badRequest().body(response);
    }
    //
}
