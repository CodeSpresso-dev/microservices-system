package ir.mahdi.sample.microservice.cards.dto.error;

public record FieldErrorDetail(
        String field,
        Object rejectedValue,
        String message
) implements ErrorDetail {}
