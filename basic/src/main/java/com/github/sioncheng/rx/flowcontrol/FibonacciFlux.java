package com.github.sioncheng.rx.flowcontrol;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

public class FibonacciFlux {

    public static final Flux<Long> FLUX = Flux.generate(()-> Tuples.of(0L, 1L),
            (state, sink) -> {
                sink.next(state.getT1());
                return Tuples.of(state.getT2(), state.getT1() + state.getT2());
            }
    );

}
