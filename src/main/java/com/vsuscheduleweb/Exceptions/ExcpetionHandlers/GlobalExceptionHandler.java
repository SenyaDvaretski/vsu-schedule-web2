package com.vsuscheduleweb.Exceptions.ExcpetionHandlers;




import com.vsuscheduleweb.Exceptions.Errors.AppError;
import com.vsuscheduleweb.Exceptions.ObjectIsPresentException;
import com.vsuscheduleweb.Exceptions.ResponseNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> responseNotFoundExceptionHandler(ResponseNotFoundException e){
        log.info(e.getMessage());
        return new ResponseEntity<AppError>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> ObjectIsPresentExceptionHandler(ObjectIsPresentException e){
        log.info(e.getMessage());
        return new ResponseEntity<AppError>(new AppError(HttpStatus.NOT_FOUND.value(),e.getMessage()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> noAuthExceptionHandler(ExpiredJwtException e){
        log.info(e.getMessage());
        return new ResponseEntity<>(new AppError(HttpStatus.FORBIDDEN.value(), e.getMessage()),HttpStatus.FORBIDDEN);
    }

}
