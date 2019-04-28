package com.github.sioncheng.rx.flowcontrol;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BackPressure {

    public static void main(String[] args) throws Exception {

        Flux<Integer> integerFlux = Flux.create(x -> {
           for (int i = 0 ; i < 100; i++) {
               x.next(i);
           }
           x.complete();
        }, FluxSink.OverflowStrategy.DROP);

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Integer> integerFlux1 = integerFlux.onBackpressureDrop(x -> System.out.println("dropped " + x));
        integerFlux1.subscribe(
                new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        System.out.println("hookOnSubscribe");
                        subscription.request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("onNext " + value);
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("hookOnComplete");
                        countDownLatch.countDown();
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        throwable.printStackTrace();
                        countDownLatch.countDown();
                    }
                }
        );

        boolean b = countDownLatch.await(1, TimeUnit.SECONDS);
        System.out.println(b);
    }
}
