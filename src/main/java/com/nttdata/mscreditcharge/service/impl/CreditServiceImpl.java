package com.nttdata.mscreditcharge.service.impl;

import com.nttdata.mscreditcharge.service.CreditService;
import com.nttdata.mscreditcharge.entity.Credit;
import com.nttdata.mscreditcharge.entity.CreditCard;
import com.nttdata.mscreditcharge.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {

    WebClient webClient = WebClient.create("http://localhost:9070/creditcard");

    @Autowired
    CreditRepository creditRepository;

    @Override
    public Mono<Credit> create(Credit t) {
        return creditRepository.save(t);
    }

    @Override
    public Flux<Credit> findAll() {
        return creditRepository.findAll();
    }

    @Override
    public Mono<Credit> findById(String id) {
        return creditRepository.findById(id);
    }

    @Override
    public Mono<Credit> update(Credit t) {
        return creditRepository.save(t);
    }

    @Override
    public Mono<Boolean> delete(String t) {
        return creditRepository.findById(t)
                .flatMap(credit -> creditRepository.delete(credit).then(Mono.just(Boolean.TRUE)))
                .defaultIfEmpty(Boolean.FALSE);
    }

    @Override
    public Mono<Long> findCountCreditCardId(String t) {
        return  creditRepository.findByCreditCardId(t).count();
    }

    @Override
    public Mono<Double> findTotalConsumptionCreditCardId(String t) {
        return  creditRepository.findByCreditCardId(t)
                .collectList()
                .map(credit -> credit.stream().mapToDouble(cdt -> cdt.getAmount()).sum());
    }

    @Override
    public Mono<CreditCard> findCreditCard(String id) {
        return webClient.get().uri("/find/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CreditCard.class);
    }
}
