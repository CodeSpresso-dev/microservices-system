package ir.mahdi.sample.microservice.cards.reository;

import ir.mahdi.sample.microservice.cards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
