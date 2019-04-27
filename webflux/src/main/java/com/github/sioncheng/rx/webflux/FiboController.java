package com.github.sioncheng.rx.webflux;

import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

@RestController
public class FiboController {


    @GetMapping("/fibonacci")
    @ResponseBody
    public Publisher<Long> fibo() {
        return fibo(3);
    }

    @GetMapping("/fibonacci/{n}")
    @ResponseBody
    public Publisher<Long> fibo(@PathVariable int n) {
        Flux<Long> fibonacciGenerator = Flux.generate(
                ()-> Tuples.of(0L, 1L),
                (state, sink) -> {
                    sink.next(state.getT1());
                    return Tuples.of(state.getT2(), state.getT1() + state.getT2());
                }

        );


        return fibonacciGenerator.take(n);
    }
}
