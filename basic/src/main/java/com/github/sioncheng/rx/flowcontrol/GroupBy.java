package com.github.sioncheng.rx.flowcontrol;

import reactor.core.publisher.Flux;

public class GroupBy {

    public static void main(String[] args) {
        Flux<Long> fibonacci = FibonacciFlux.FLUX;

        fibonacci.take(10).groupBy(x ->  x % 2 == 0 ? "odd":"even")
                .subscribe(stringLongGroupedFlux ->
                    stringLongGroupedFlux.subscribe(v -> System.out.println(stringLongGroupedFlux.key() + " " + v))
                );

        fibonacci.take(10).buffer(2).subscribe(System.out::println);
    }
}
