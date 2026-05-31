package ir.mahdi.sample.microservice.cards.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FetchCardsResponse {

    private String mobileNumber;

    private List<CardResponse> cards;

    private int totalCards;
}
