package io.github.metaphor.council.bootcamp.ddd.framework.web;

import io.github.metaphor.council.bootcamp.ddd.framework.domain.exception.BizException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.UpperSnakeCaseStrategy.INSTANCE;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnhandledException(RuntimeException e) {
        return ErrorResponse.builder()
            .code("UNKNOWN_ERROR")
            .message("Please contact administrator")
            .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(BizException e) {
        return ErrorResponse.builder()
            .code(INSTANCE.translate(e.getClass().getSimpleName().replace("Exception", "")))
            .message(e.getMessage())
            .build();
    }
}
