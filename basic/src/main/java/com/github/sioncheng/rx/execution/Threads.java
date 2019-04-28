package com.github.sioncheng.rx.execution;

import com.github.sioncheng.rx.common.FibonacciFlux;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Threads {

    public static void main(String[] args) throws Exception{
        println("main");

        Flux<Long> flux = FibonacciFlux.FLUX;

        flux.delaySubscription(Duration.ofSeconds(1), Schedulers.single())
                .take(10)
                .doFinally(x->println(x))
                .subscribe(x -> println(x));


        Flux<Integer> flux1 = Flux.fromIterable(IntStream.range(1, 20).boxed().collect(Collectors.toList()));
        flux1.publishOn(Schedulers.elastic())
                .subscribe(Threads::println);

        Flux<Integer> flux2 = Flux.fromIterable(IntStream.range(1, 20).boxed().collect(Collectors.toList()));
        flux2.subscribeOn(Schedulers.elastic())
                .subscribe(Threads::println);


        Thread.sleep(2000);
    }

    static void println(Object o) {
        System.out.println(o.toString() +  " " + Thread.currentThread().getName());
    }
}
