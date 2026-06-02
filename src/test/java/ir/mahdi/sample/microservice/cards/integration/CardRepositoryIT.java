package ir.mahdi.sample.microservice.cards.integration;

import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class CardRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private CardRepository cardRepository;

    @Test
    @DisplayName("""
            Given a valid card
            When save is called
            Then card should be persisted
            """)
    void shouldSaveCardSuccessfully() {

        Card card = buildCard();

        Card savedCard = cardRepository.save(card);

        assertThat(savedCard.getId()).isNotNull();
    }

    @Test
    @DisplayName("""
            Given an existing card
            When findById is called
            Then card should be returned
            """)
    void shouldFindCardById() {

        Card savedCard = cardRepository.save(buildCard());

        Optional<Card> result =
                cardRepository.findById(savedCard.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getCardNumber())
                .isEqualTo(savedCard.getCardNumber());
    }

    @Test
    @DisplayName("""
            Given an existing card
            When delete is called
            Then card should not exist
            """)
    void shouldDeleteCardSuccessfully() {

        Card savedCard = cardRepository.save(buildCard());

        cardRepository.delete(savedCard);

        assertThat(
                cardRepository.findById(savedCard.getId())
        ).isEmpty();
    }

    private Card buildCard() {

        Card card = new Card();

        card.setMobileNumber("09121234567");
        card.setCardNumber("6037991234567890");
        card.setCardHolderName("Mahdi Shivaeifar");
        card.setExpiryDate("12/30");
        card.setCvv("123");
        card.setCardType("DEBIT");
        card.setStatus("ACTIVE");

        return card;
    }
}
