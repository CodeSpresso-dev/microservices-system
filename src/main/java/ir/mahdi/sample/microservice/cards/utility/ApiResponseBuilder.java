package ir.mahdi.sample.microservice.cards.utility;

import ir.mahdi.sample.microservice.cards.dto.response.ApiError;
import ir.mahdi.sample.microservice.cards.dto.response.ApiResponse;

import java.time.Instant;

/**
 * Utility Factory Methods
 */
public class ApiResponseBuilder {

    public static <T> ApiResponse<T> success(T data, String message) {

        return new ApiResponse<>(
                true,
                message,
                data,
                null,
                Instant.now()
        );
    }

    public static <T> ApiResponse<T> failure(
            String message,
            ApiError error
    ) {

        return new ApiResponse<>(
                false,
                message,
                null,
                error,
                Instant.now()
        );
    }

}
