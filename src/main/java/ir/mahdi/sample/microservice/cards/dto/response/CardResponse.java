package ir.mahdi.sample.microservice.cards.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {

    private Long cardId;

    private String mobileNumber;

    private String maskedCardNumber; // **** **** **** 1234

    private String cardHolderName;

    private String cardType;

    private String expiryDate;

    private String status; // ACTIVE / BLOCKED

    private String createdAt;
}
