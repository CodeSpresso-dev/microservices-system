package ir.mahdi.sample.microservice.cards.service;

import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.dto.response.CardResponse;

public interface CardService {

    CardResponse createCard(CreateCardRequest request);

}
