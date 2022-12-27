package com.example.reactiveprogrammingallpractice.Services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Flux_MonoServices {

    public Flux<String> fluxServices(){
        return Flux.just("Mango", "Apple", "PineApple");
    }

    public Flux<String> fluxServicesMap(){
        return Flux.just("Mango", "Apple", "PineApple")
                .map(String::toUpperCase);
    }

    public Flux<String> FruitsFluxFilter(Integer a){
        return  Flux.fromIterable(List.of("Mango", "Apple", "PineApple"))
                .filter(i -> i.length() > a);
    }

    public Flux<String> FruitsFluxFilterMap(Integer a){
        return  Flux.fromIterable(List.of("Mango", "Apple", "PineApple"))
                .filter(i -> i.length() > a)
                .map(String::toLowerCase);
    }

    public Flux<String> fruitsFluxFlatMap(){
        return  Flux.fromIterable(List.of("Mango", "Apple", "PineApple"))
                .flatMap(i -> Flux.just(i.split("")));
    }

   public Mono<String> monoService(){
        return Mono.just("Lichi");
   }



    public static void main(String[] args) {
        Flux_MonoServices fluxMonoServices = new Flux_MonoServices();
        fluxMonoServices.fluxServices().subscribe(
                s -> System.out.println(s)
        );

        fluxMonoServices.monoService().subscribe(
                s-> System.out.println(s)
        );

    }




}
