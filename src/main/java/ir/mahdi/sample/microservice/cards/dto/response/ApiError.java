package ir.mahdi.sample.microservice.cards.dto.response;

import ir.mahdi.sample.microservice.cards.dto.error.ErrorDetail;

import java.time.Instant;
import java.util.List;

public record ApiError(String code,

                       String message,

                       String type,

                       String path,

                       Instant errorTime,

                       String correlationId,

                       List<ErrorDetail> subErrors) {
}
