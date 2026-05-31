package ir.mahdi.sample.microservice.cards.service;

import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.dto.response.CardResponse;
import ir.mahdi.sample.microservice.cards.dto.response.FetchCardsResponse;

public interface CardService {

    CardResponse createCard(CreateCardRequest request);

}
