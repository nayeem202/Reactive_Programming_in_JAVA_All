package com.example.reactiveprogrammingallpractice.Services;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class Flux_MonoServicesTest {
    Flux_MonoServices fluxMonoServices = new Flux_MonoServices();
    @Test
    void fluxServices() {
          Flux<String> fruits =  fluxMonoServices.fluxServices();
        StepVerifier.create(fruits)
                .expectNext("Mango", "Apple", "PineApple")
                .verifyComplete();

    }

    @Test
    void monoService() {
        Mono<String> fruits =  fluxMonoServices.monoService();
        StepVerifier.create(fruits)
                .expectNext("Lichi")
                .verifyComplete();
    }

    @Test
    void fluxServicesMap() {
        Flux<String> fruits =  fluxMonoServices.fluxServicesMap();
        StepVerifier.create(fruits)
                .expectNext("MANGO", "APPLE", "PINEAPPLE")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        Flux<String> fruits =  fluxMonoServices.FruitsFluxFilter(6);

        StepVerifier.create(fruits)
                .expectNext("PineApple")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterMap() {
        Flux<String> fruits = fluxMonoServices.FruitsFluxFilterMap(7);
        StepVerifier.create(fruits)
                .expectNext("pineapple")
                .verifyComplete();

    }

    @Test
    void fruitsFluxFlatMap() {
       Flux<String> fruits =  fluxMonoServices.fruitsFluxFlatMap();
       StepVerifier.create(fruits)
               .expectNextCount(19)
               .verifyComplete();

    }
}