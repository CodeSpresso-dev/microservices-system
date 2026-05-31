package ir.mahdi.sample.microservice.cards.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cards")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String cardHolderName;

    @Column(nullable = false, unique = true)
    private String cardNumber;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false)
    private String cardType; // DEBIT / CREDIT

    @Column(nullable = false)
    private String expiryDate;

    @Column(nullable = false)
    private String status; // ACTIVE / BLOCKED / PENDING
}