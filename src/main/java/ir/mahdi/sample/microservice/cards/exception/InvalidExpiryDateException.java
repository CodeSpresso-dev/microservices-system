package ir.mahdi.sample.microservice.cards.exception;

public class InvalidExpiryDateException extends CardException {
    public InvalidExpiryDateException(String expiryDate) {
        super(ErrorCode.INVALID_EXPIRY_DATE_EXCEPTION, expiryDate);
    }
}
