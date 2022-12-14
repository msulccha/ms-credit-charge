package com.nttdata.mscreditcharge.repository;

import com.nttdata.mscreditcharge.entity.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {

    Flux<Credit> findByCreditCardId(String id);

}
