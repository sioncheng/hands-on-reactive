package com.github.sioncheng.rx.basic;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicBoolean;

public class FluxAndSink {

    public static void main(String[] args) {

        Flux<Long> fibonacciFlux = Flux.create(e->{
           long prev = 0;
           long current = 1;

            AtomicBoolean stop = new AtomicBoolean(false);

            e.onDispose(()->stop.set(true));

            while (!stop.get() && current > 0) {
                e.next(current);
                long next = current + prev;
                prev = current;
                current =next;
            }

            e.complete();
        });

        fibonacciFlux.take(10).subscribe(System.out::println);
    }
}
