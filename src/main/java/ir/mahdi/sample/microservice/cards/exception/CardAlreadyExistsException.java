package ir.mahdi.sample.microservice.cards.exception;

public class CardAlreadyExistsException extends CardException {
    public CardAlreadyExistsException(String cardNumber) {
        super(ErrorCode.CARD_ALREADY_EXISTS_EXCEPTION, cardNumber);
    }
}
