package ru.neoflex.VacationCalculate.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
    logger.error("ПерехваченоIllegalArgumentException: {}",ex.getMessage(),ex);
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
}
@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
    logger.error("Перехвачено неожиданное исключение: {}", ex.getMessage(),ex);
    return new ResponseEntity<>("Произошла ошибка на сервере. Пожалуйста повторите попытку позже", HttpStatus.INTERNAL_SERVER_ERROR);
}

}
