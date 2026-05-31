package ir.mahdi.sample.microservice.cards.bdd.builder;

import ir.mahdi.sample.microservice.cards.entity.Card;

public class CardBuilder {
    public static Card DefaultCard() {
        return Card.builder()
                .mobileNumber("09123456789")
                .cardHolderName("Valid User")
                .cardNumber("100200321")
                .cvv("123")
                .expiryDate("202605")
                .cardType("DEBIT")
                .status("ACTIVE")
                .build();
    }
}
