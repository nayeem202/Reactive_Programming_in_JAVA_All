package com.example.reactiveprogrammingallpractice.services;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotAndColdStreamsTest {
    @Test
    public void coldstreamTest()
    {
        var numbers  = Flux.range(1,10);
    numbers.subscribe(integer -> System.out.println("subscribe 1 ="+ integer));
    numbers.subscribe(integer -> System.out.println("Subscribe 2 = " + integer));
    }

    @Test
    public  void hotStreamTest() throws InterruptedException {
        var numbers = Flux.range(1,10)
                .delayElements(Duration.ofMillis(1000));
        ConnectableFlux<Integer> publisher = numbers.publish();
        publisher.connect();

        publisher.subscribe(integer -> System.out.println("Subscriber 1= "+ integer ));
        Thread.sleep(2000);

        publisher.subscribe(integer -> System.out.println("Subscriber 2= "+ integer ));
        Thread.sleep(10000);

    }

}
