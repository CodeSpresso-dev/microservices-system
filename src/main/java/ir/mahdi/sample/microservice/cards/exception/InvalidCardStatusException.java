package ir.mahdi.sample.microservice.cards.exception;

public class InvalidCardStatusException extends CardException {
    public InvalidCardStatusException(String status) {
        super(ErrorCode.INVALID_CARD_STATUS_EXCEPTION, status);
    }
}
