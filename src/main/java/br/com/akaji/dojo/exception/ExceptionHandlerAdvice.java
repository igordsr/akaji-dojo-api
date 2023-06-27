package br.com.akaji.dojo.exception;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.akaji.dojo.common.Functions;

@Log4j2
@ControllerAdvice
public class ExceptionHandlerAdvice {
    private static final String UNKNOWN_ERROR_KEY = "akaji.error.handler.unknown.error";
    private final MessageSource messageSource;

    public ExceptionHandlerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorHandlerDto> handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        log.warn(exception);
        Set<ErrorHandlerDto> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> buildError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toSet());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors));
    }

    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<ApiErrorHandlerDto> handlerBaseException(Throwable exception) {
        log.error(exception);

        MessageException messageException = (MessageException) exception;
        ErrorHandlerDto error = buildError(messageException.getExceptionKey(),
                bindExceptionKeywords(messageException.getMapDetails(), messageException.getExceptionKey()));

        Set<ErrorHandlerDto> errors = Set.of(error);
        ApiErrorHandlerDto apiErrorDto = baseErrorBuilder(getResponseStatus(exception), errors);

        return ResponseEntity.status(getResponseStatus(exception)).body(apiErrorDto);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorHandlerDto> handlerMethodThrowable(Throwable exception) {
        log.error(exception.getMessage(), exception);
        Set<ErrorHandlerDto> errors = Set.of(buildError(UNKNOWN_ERROR_KEY, this.bindExceptionKeywords(null, UNKNOWN_ERROR_KEY)));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(baseErrorBuilder(HttpStatus.INTERNAL_SERVER_ERROR, errors));
    }

    @ExceptionHandler(AKAJIException.class)
    public ResponseEntity<ApiErrorHandlerDto> handlerAkajiThrowable(Throwable exception) {
        AKAJIException akajiException = (AKAJIException) exception;
        log.warn(exception.getMessage());
        Set<ErrorHandlerDto> errors = Set.of(buildError(akajiException.getCode(), exception.getMessage()));
        return ResponseEntity.status(akajiException.getHttpStatus())
                .body(baseErrorBuilder(akajiException.getHttpStatus(), errors));
    }

    private ErrorHandlerDto buildError(String code, String message) {
        return new ErrorHandlerDto(code, message);
    }

    private ApiErrorHandlerDto baseErrorBuilder(HttpStatus httpStatus, Set<ErrorHandlerDto> errorList) {
        return new ApiErrorHandlerDto(new Date(), httpStatus.value(), httpStatus.name(), errorList);
    }

    private String bindExceptionKeywords(Map<String, Object> keywords, String exceptionKey) {
        return Functions.getMessageFromProperties(this.messageSource, keywords, exceptionKey);
    }

    private HttpStatus getResponseStatus(Throwable exception) {
        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
        if (exception.getClass().getAnnotation(ResponseStatus.class) == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return responseStatus.value();
    }
}