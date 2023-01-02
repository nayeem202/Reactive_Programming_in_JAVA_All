package com.example.reactiveprogrammingallpractice.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

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

    public Flux<String> fruitsFluxFlatMapAsync(){
        return Flux.fromIterable(List.of("Mango","Orange", "Banana"))
                .flatMap(s -> Flux.just(s.split(""
                )).delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ))).log();
    }

    public Flux<String> fruitsFluxConcatMap(){
        return Flux.fromIterable(List.of("Mango","Orange", "Banana"))
                .concatMap(s -> Flux.just(s.split(""
                )).delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ))).log();
    }





    public Mono<List<String>> fruitsMonoFlatMap(){
        return Mono.just("Mango")
                .flatMap(s-> Mono.just(List.of(s.split("")))).log();
    }


    //***************flat map many******************
    public Flux<String> fruitsMonoFlatMapMany(){
        return Mono.just("Mango")
                .flatMapMany(s-> Flux.just(s.split(""))).log();
    }


    //**************transfer operator***************
    public Flux<String> fruitsFluxTransform(int number){
        Function<Flux <String>,Flux<String>> filterData
                = data -> data.filter(s-> s.length() > number);

        return  Flux.fromIterable(List.of("Mango", "Orange","Banana"))
                .transform(filterData)
                .log();

    }


    public Flux<String> fruitsFluxTransformDefaultIfEmpty(int number){
        Function<Flux <String>,Flux<String>> filterData
                = data -> data.filter(s-> s.length() > number);

        return  Flux.fromIterable(List.of("Mango", "Orange","Banana"))
                .transform(filterData)
                .defaultIfEmpty("Default")
                .log();
    }

    //**************Switch if empty operator***********************8
    public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number){
        Function<Flux <String>,Flux<String>> filterData
                = data -> data.filter(s-> s.length() > number);

        return  Flux.fromIterable(List.of("Mango", "Orange","Banana"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("Pineapple","Jackfruit")
                        .transform(filterData))
                .log();

    }


    //*************************Merge**********************
    public Flux<String> fruitsMerge(){
        var fruits1 = Flux.just("Apple", "Banana")
                .delayElements(Duration.ofMillis(40)).log();
        var Fruits2 = Flux.just("PineApple", "Lichi")
                .delayElements(Duration.ofMillis(55)).log();
       /*return fruits1.merge(Fruits2);
*/
        return Flux.merge(fruits1,Fruits2);
    }
    //****************************************Merge With******************************8
    public Flux<String> fruitsMergeWith(){
        var fruits1 = Flux.just("Apple", "Banana")
                .delayElements(Duration.ofMillis(40));
        var Fruits2 = Flux.just("PineApple", "Lichi")
                .delayElements(Duration.ofMillis(55));
        return fruits1.mergeWith(Fruits2);
    }
   //**********************Merge Sequential*******************
   public Flux<String> fruitsMergeWitSequential(){

       var fruits1 = Flux.just("Apple", "Banana")
               .delayElements(Duration.ofMillis(40));
       var fruits2 = Flux.just("PineApple", "Lichi")
               .delayElements(Duration.ofMillis(55));
       return Flux.mergeSequential(fruits1,  fruits2);

   }

    //**********************zip*****************************************************
    public Flux<String> fruitsFluxZip(){
        var fruits1 = Flux.just("Apple", "PineApple");
        var fruits2 = Flux.just("Mango", "Orange");
        return Flux.zip(fruits1,fruits2, (first,second) -> first+second).log();
    }

    public Flux<String> fruitsFluxWithZip(){
        var  fruits1 = Flux.just("Apple", "PineApple");
        var fruits2 = Flux.just("Mango", "Orange");
        return fruits1.zipWith(fruits2, (first,second) -> first + second).log();
    }

    public Flux<String> fruitsFluxZipTuple(){
        var fruits = Flux.just("Apple", "PineApple");
        var vegetables = Flux.just("Bean", "Tometo");
        var moreVegetables =  Flux.just("cucumber", "poteto");
        return Flux.zip(fruits,vegetables, moreVegetables)
                .map(objects -> objects.getT1()+ objects.getT2() + objects.getT2());
    }

    //******************************************DoOn****************************
    public Flux<String> fuitsFluxFilterDoOn(Integer number){
        return Flux.fromIterable(List.of("Mango", "Apple", "Banana"))
                .filter( s -> s.length() > number)
                .doOnNext(s -> {
                    System.out.println("s "+s);
                })
                .doOnSubscribe(subscription -> {
                    System.out.println("Subscription.toString" + subscription.toString());
                })
                .doOnComplete(() -> System.out.println("Completed"));
    }



    //*********************Error Return*************************
    public Flux<String> fruitsFluxErrorReturn(){
        return Flux.just("Apple", "Mango")
                .concatWith(Flux.error(new RuntimeException("Exception Occured")))
                        .onErrorReturn("Orange");
    }

    //********************OnerrorContinue***********************


    public Flux<String> fruitsFluxOnErrorContinue(){
      return Flux.just("Apple", "Mango", "Orange")
              .map(s -> {
                  if(s.equalsIgnoreCase("Mango"))
                      throw new RuntimeException("exception occured");
                      return s.toUpperCase();
              })
              .onErrorContinue((e,f) -> {
                  System.out.println("e = " + e);
                  System.out.println("f = " + f);
              });
    }

    //****************************************On error map*******************************

    public Flux<String> fruitFluxOnErrorMap(){
        return Flux.just("Apple", "Mango", "Orange")
                .checkpoint("Error Check Point 1")
                .map(s -> {
                    if(s.equalsIgnoreCase("Mango"))
                        throw new RuntimeException("exception occured");
                    return s.toUpperCase();
                })
                .checkpoint("Error Check Point 2")
                .onErrorMap(throwable -> {
                        System.out.println("Thorwable =" + throwable);
                        return new IllegalStateException("From onError map");
                });
    }



    //********************************Do on Error ********************8
    public Flux<String> fruitFluxDoOnError(){
        return Flux.just("Apple", "Mango", "Orange")
                .map(s -> {
                    if(s.equalsIgnoreCase("Mango"))
                        throw new RuntimeException("exception occured");
                    return s.toUpperCase();
                })
                .doOnError(throwable -> {
                    System.out.println("Thorwable =" + throwable);

                });
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
