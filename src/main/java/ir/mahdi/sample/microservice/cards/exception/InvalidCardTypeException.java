package ir.mahdi.sample.microservice.cards.exception;

public class InvalidCardTypeException extends CardException {
    public InvalidCardTypeException(String cardType) {
        super(ErrorCode.INVALID_CARD_TYPE_EXCEPTION, cardType);
    }
}
