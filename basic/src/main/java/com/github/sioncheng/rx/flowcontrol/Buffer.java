package com.github.sioncheng.rx.flowcontrol;

import reactor.core.publisher.Flux;

public class Buffer {

    public static void main(String[] args) {
        Flux<Long> fibonacci = FibonacciFlux.FLUX;
        fibonacci.take(10).buffer(2).subscribe(System.out::println);
    }
}
