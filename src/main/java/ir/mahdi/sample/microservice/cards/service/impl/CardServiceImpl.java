package ir.mahdi.sample.microservice.cards.service.impl;

import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.dto.response.CardResponse;
import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import ir.mahdi.sample.microservice.cards.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    /**
     * Creates a new card for the given customer request.
     *
     * @param request contains card creation details such as mobile number
     * @return created card response with card details
     */
    @Override
    public CardResponse createCard(CreateCardRequest request) {

        //1. todo validation
        // if card exists throw business exception

        // 2. build entity
        Card card = Card.builder()
                .mobileNumber(request.getMobileNumber())
                .cardHolderName(request.getCardHolderName())
                .cardNumber(request.getCardNumber())
                .cvv(request.getCvv())
                .cardType(request.getCardType())
                .expiryDate(request.getExpiryDate())
                .status("ACTIVE")
                .build();

        Card saved = cardRepository.save(card);

        return mapToResponse(saved);
    }

    // ---------------- helper ----------------

    private CardResponse mapToResponse(Card card) {
        return CardResponse.builder()
                .cardId(card.getId())
                .mobileNumber(card.getMobileNumber())
                .maskedCardNumber(mask(card.getCardNumber()))
                .cardHolderName(card.getCardHolderName())
                .cardType(card.getCardType())
                .expiryDate(card.getExpiryDate())
                .status(card.getStatus())
                .createdAt(card.getCreated_at().toString())
                .build();
    }

    private String mask(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) return "INVALID";
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
}
