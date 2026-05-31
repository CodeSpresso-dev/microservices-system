package ir.mahdi.sample.microservice.cards.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    CARD_ALREADY_EXISTS_EXCEPTION("CARD-001", "error.card.already.exists", HttpStatus.CONFLICT);

    private final String code;
    private final String messageKey;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String messageKey, HttpStatus httpStatus) {
        this.code = code;
        this.messageKey = messageKey;
        this.httpStatus = httpStatus;
    }
}
