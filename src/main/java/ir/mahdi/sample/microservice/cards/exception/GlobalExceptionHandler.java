package ir.mahdi.sample.microservice.cards.exception;

import ir.mahdi.sample.microservice.cards.dto.error.ErrorDetail;
import ir.mahdi.sample.microservice.cards.dto.error.ErrorType;
import ir.mahdi.sample.microservice.cards.dto.error.FieldErrorDetail;
import ir.mahdi.sample.microservice.cards.dto.response.ApiError;
import ir.mahdi.sample.microservice.cards.dto.response.ApiResponse;
import ir.mahdi.sample.microservice.cards.utility.ApiResponseBuilder;
import ir.mahdi.sample.microservice.cards.utility.CorrelationIdUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String DEFAULT_ERROR_MESSAGE = "Request processing failed";

    private static final String GLOBAL_EXCEPTION_MESSAGE_KEY = "error.global.exception";

    private final MessageSource messageSource;

    // =========================
    // 1. GLOBAL EXCEPTION
    // =========================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGlobalException(Exception ex, WebRequest webRequest) {

        HttpStatus errorHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        String resolvedMessage = messageSource.getMessage(
                GLOBAL_EXCEPTION_MESSAGE_KEY,
                null,
                LocaleContextHolder.getLocale()
        );

        logger.error(
                "Exception occurred. Exception type={}, code={}, message={}",
                ex.getClass().getSimpleName(),
                errorHttpStatus.value(),
                resolvedMessage,
                ex
        );

        ApiError apiError = new ApiError(
                "SYS_001",
                resolvedMessage,
                ErrorType.SYSTEM_ERROR.name(),
                webRequest.getDescription(false),
                Instant.now(),
                CorrelationIdUtils.getCorrelationId(),
                null
        );

        return buildErrorResponse(errorHttpStatus, apiError);
    }

    // =========================
    // 2. VALIDATION ERROR
    // =========================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        HttpStatus errorHttpStatus = HttpStatus.BAD_REQUEST;

        String validationError = "validation.customer.error";

        String resolvedMessage = messageSource.getMessage(
                validationError,
                null,
                LocaleContextHolder.getLocale()
        );

        List<ErrorDetail> details = ex.getBindingResult().getFieldErrors().stream()
                .map(this::mapFieldError)
                .map(e -> (ErrorDetail) e)
                .toList();

        logger.warn(
                "Exception occurred. Exception type={}, code={}, message={}",
                ex.getClass().getSimpleName(),
                errorHttpStatus.value(),
                resolvedMessage,
                ex
        );

        ApiError apiError = new ApiError(
                "VALIDATION_ERROR",
                resolvedMessage,
                ErrorType.VALIDATION_ERROR.name(),
                request.getDescription(false),
                Instant.now(),
                CorrelationIdUtils.getCorrelationId(),
                details
        );

        return buildErrorResponse(errorHttpStatus, apiError);
    }

    private FieldErrorDetail mapFieldError(FieldError err) {
        return new FieldErrorDetail(
                err.getField(),
                err.getRejectedValue(),
                err.getDefaultMessage()
        );
    }

    // =========================
    // 3. BUSINESS ERROR
    // =========================
    @ExceptionHandler(CardException.class)
    public ResponseEntity<ApiResponse<Void>> handleCardException(CardException ex, WebRequest webRequest) {

        HttpStatus errorHttpStatus = ex.getErrorCode().getHttpStatus();

        ApiError apiError = buildApiError(ex, webRequest);

        return buildErrorResponse(errorHttpStatus, apiError);
    }

    private ApiError buildApiError(CardException ex, WebRequest request) {

        String resolvedMessage = messageSource.getMessage(
                ex.getErrorCode().getMessageKey(),
                ex.getArgs(),
                LocaleContextHolder.getLocale()
        );

        logger.warn(
                "Business Exception occurred.Exception type={}, code={}, message={}",
                ex.getClass().getSimpleName(),
                ex.getErrorCode().getCode(),
                resolvedMessage,
                ex
        );

        return new ApiError(
                ex.getErrorCode().getCode(),
                resolvedMessage,
                ErrorType.BUSINESS_ERROR.name(),
                request.getDescription(false),
                Instant.now(),
                CorrelationIdUtils.getCorrelationId(),
                null
        );
    }

    private ResponseEntity<ApiResponse<Void>> buildErrorResponse(
            HttpStatus status,
            ApiError apiError) {

        return ResponseEntity.status(status)
                .body(ApiResponseBuilder.failure(DEFAULT_ERROR_MESSAGE, apiError));
    }
}
