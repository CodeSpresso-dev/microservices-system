package ir.mahdi.sample.microservice.cards.dto.response;

import java.time.Instant;

public record ApiResponse<T>(


        boolean success,


        String message,


        T data,


        ApiError error,


        Instant timestamp

) {
}
