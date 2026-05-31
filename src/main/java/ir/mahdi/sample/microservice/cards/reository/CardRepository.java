package ir.mahdi.sample.microservice.cards.reository;

import ir.mahdi.sample.microservice.cards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    boolean existsByCardNumber(String cardNumber);

    List<Card> findByMobileNumber(String mobileNumber);
}
