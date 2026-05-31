package ir.mahdi.sample.microservice.cards.exception;

public class ResourceNotFoundException extends CardException {
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
         super(ErrorCode.RESOURCE_NOT_FOUND, resourceName, fieldName, fieldValue);
    }
}
