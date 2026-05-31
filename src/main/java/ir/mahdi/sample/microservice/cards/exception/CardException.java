package ir.mahdi.sample.microservice.cards.exception;

import lombok.Getter;

import java.util.Objects;

@Getter
public class CardException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Object[] args;

    public CardException(ErrorCode errorCode, Object... args) {
        super(errorCode.getCode());
        this.errorCode = Objects.requireNonNull(errorCode, "ErrorCode cannot be null");
        this.args = args == null ? new Object[0] : args;
    }
}
