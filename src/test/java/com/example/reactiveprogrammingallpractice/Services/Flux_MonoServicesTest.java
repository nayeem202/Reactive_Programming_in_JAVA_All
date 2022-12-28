package com.example.reactiveprogrammingallpractice.Services;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

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

    @Test
    void fruitsFluxFlatMapAsync() {
           Flux<String> fruitsFlux = fluxMonoServices.fruitsFluxFlatMapAsync();
            StepVerifier.create(fruitsFlux)
                    .expectNextCount(17)
                    .verifyComplete();
    }


    @Test
    void fruitsMonoFlatMap() {
       Mono<List<String>> fruitsFlux = fluxMonoServices.fruitsMonoFlatMap();
       StepVerifier.create(fruitsFlux)
               .expectNextCount(1)
               .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMap() {
        Flux<String> fruitsFlux = fluxMonoServices.fruitsFluxConcatMap();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsMonoFlatMapMany() {
        Flux<String> fruitsFlux = fluxMonoServices.fruitsMonoFlatMapMany();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(5)
                .verifyComplete();
    }


    @Test
    void fruitsFluxTransform() {
        Flux<String> fruits =  fluxMonoServices.fruitsFluxTransform(5);

        StepVerifier.create(fruits)
                .expectNext("Orange", "Banana ")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformDefaultIfEmpty() {

        Flux<String> fruits =  fluxMonoServices.fruitsFluxTransformDefaultIfEmpty(10);

        StepVerifier.create(fruits)
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformSwitchIfEmpty() {
        Flux<String> fruits =  fluxMonoServices.fruitsFluxTransformSwitchIfEmpty(8);

        StepVerifier.create(fruits)
                .expectNext("Pineapple", "Jackfruit")
                .verifyComplete();
    }

    @Test
    void fruitsMerge() {
        Flux<String> fruits =  fluxMonoServices.fruitsMerge();
        StepVerifier.create(fruits)
                .expectNext("Apple", "PineApple", "Banana", "Lichi")
                .verifyComplete();
    }

    @Test
    void fruitsMergeWith() {
        Flux<String> fruits =  fluxMonoServices.fruitsMergeWith();
        StepVerifier.create(fruits)
                .expectNext("Apple", "PineApple", "Banana", "Lichi")
                .verifyComplete();
    }

    @Test
    void fruitsMergeWitSequential() {
           Flux<String> fruits =  fluxMonoServices.fruitsMergeWitSequential();
        StepVerifier.create(fruits)
                .expectNext("Apple", "Banana", "PineApple" , "Lichi")
                .verifyComplete();
    }
}