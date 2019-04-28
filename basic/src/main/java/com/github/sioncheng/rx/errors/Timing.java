package com.github.sioncheng.rx.errors;

import com.github.sioncheng.rx.common.FibonacciFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public class Timing {

    public static void main(String[] args) throws Exception{

        Flux<Long> flux = FibonacciFlux.FLUX;

        CountDownLatch countDownLatch = new CountDownLatch(1);
        flux.delayElements(Duration.ofSeconds(1))
                .timeout(Duration.ofMillis(500), Flux.just(-1L))
                .doFinally(x-> countDownLatch.countDown())
                .subscribe(System.out::println, e->e.printStackTrace());
        countDownLatch.await();
    }
}
