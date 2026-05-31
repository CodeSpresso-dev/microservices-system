package ir.mahdi.sample.microservice.cards.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    CARD_ALREADY_EXISTS_EXCEPTION("CARD-001", "error.card.already.exists", HttpStatus.CONFLICT),
    INVALID_CARD_TYPE_EXCEPTION("CARD-002", "error.card.invalid.card.type", HttpStatus.BAD_REQUEST),
    INVALID_EXPIRY_DATE_EXCEPTION("CARD-003", "error.card.invalid.expiry.date", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND("CARD-004", "error.card.resource.not.found.exception", HttpStatus.NOT_FOUND);

    private final String code;
    private final String messageKey;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String messageKey, HttpStatus httpStatus) {
        this.code = code;
        this.messageKey = messageKey;
        this.httpStatus = httpStatus;
    }
}
