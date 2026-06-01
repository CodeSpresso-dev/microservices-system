package ir.mahdi.sample.microservice.cards.bdd.builder;

import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;

public class CreateCardRequestBuilder {

    public static CreateCardRequest valid() {

        CreateCardRequest request = new CreateCardRequest();

        request.setMobileNumber("09123456789");
        request.setCardHolderName("Mehdi");
        request.setCardNumber("1234567892548962");
        request.setCvv("123");
        request.setExpiryDate("202605");
        request.setCardType("DEBIT");

        return request;
    }
}
