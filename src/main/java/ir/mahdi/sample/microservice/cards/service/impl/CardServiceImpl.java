package ir.mahdi.sample.microservice.cards.service.impl;

import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.dto.response.CardResponse;
import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.exception.CardAlreadyExistsException;
import ir.mahdi.sample.microservice.cards.exception.InvalidCardTypeException;
import ir.mahdi.sample.microservice.cards.exception.InvalidExpiryDateException;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import ir.mahdi.sample.microservice.cards.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private static final List<String> VALID_CARD_TYPES = List.of("CREDIT", "DEBIT");

    /**
     * Creates a new card for the given customer request.
     *
     * @param request contains card creation details such as mobile number
     * @return created card response with card details
     */
    @Override
    public CardResponse createCard(CreateCardRequest request) {

        checkInvalidCardType(request);

        if (request.getExpiryDate() == null ||
                request.getExpiryDate().length() != 6) {
            throw new InvalidExpiryDateException(request.getExpiryDate());
        }

        checkCardExistence(request);

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

    /**
     * Retrieves all cards associated with a mobile number.
     *
     * @param mobileNumber the customer's mobile number
     * @return list of cards linked to the given mobile number
     */
    @Override
    public List<CardResponse> fetchCards(String mobileNumber) {

        List<Card> cards = cardRepository.findByMobileNumber(mobileNumber);

        return cards.stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ---------------- helper ----------------

    private void checkInvalidCardType(CreateCardRequest request) {
        if (!VALID_CARD_TYPES.contains(request.getCardType())) {
            throw new InvalidCardTypeException(request.getCardType());
        }
    }

    private void checkCardExistence(CreateCardRequest request) {
        if (cardRepository.existsByCardNumber(request.getCardNumber())) {
            throw new CardAlreadyExistsException(
                    request.getCardNumber()
            );
        }
    }

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
