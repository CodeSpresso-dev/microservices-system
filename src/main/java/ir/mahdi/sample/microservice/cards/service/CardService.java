package ir.mahdi.sample.microservice.cards.service;

import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.dto.response.CardResponse;

import java.util.List;

public interface CardService {

    CardResponse createCard(CreateCardRequest request);

    List<CardResponse> fetchCards(String mobileNumber);

    void deleteCard(Long cardId);

    CardResponse updateStatus(Long cardId, String status);

}
