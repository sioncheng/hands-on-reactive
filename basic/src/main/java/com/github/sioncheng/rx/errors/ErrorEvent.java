package com.github.sioncheng.rx.errors;

import com.github.sioncheng.rx.flowcontrol.FibonacciFlux;
import reactor.core.publisher.Flux;

public class ErrorEvent {

    public static void main(String[] args) {

        Flux<Long> flux = FibonacciFlux.FLUX_WITH_ERROR;

        flux.doFinally(x->System.out.println(x)).subscribe(x -> System.out.println(x), e -> e.printStackTrace());
    }
}
