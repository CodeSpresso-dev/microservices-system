package ir.mahdi.sample.microservice.cards.service;

import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import ir.mahdi.sample.microservice.cards.service.impl.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CardServiceTest {
    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardServiceImpl cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}