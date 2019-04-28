package com.github.sioncheng.rx.basic;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.ReplayProcessor;

public class Processors {

    public static void main(String[] args) {

        DirectProcessor<Integer> directProcessor = DirectProcessor.create();
        directProcessor.subscribe(System.out::println);
        directProcessor.onNext(1);
        directProcessor.onNext(2);
        directProcessor.subscribe(System.out::println);
        directProcessor.onNext(3);
        directProcessor.onComplete();

        EmitterProcessor<Integer> emitterProcessor = EmitterProcessor.create();
        emitterProcessor.subscribe(System.out::println);
        FluxSink sink = emitterProcessor.sink();
        sink.next(100);
        sink.next(200);
        emitterProcessor.subscribe(System.out::println);
        sink.next(300);

        sink.complete();

        ReplayProcessor<Integer> replayProcessor = ReplayProcessor.create();
        replayProcessor.subscribe(System.out::println);
        FluxSink sink2 = replayProcessor.sink();
        sink2.next(3);
        sink2.next(5);
        sink2.next(7);
        replayProcessor.subscribe(System.out::println);
        sink2.next(9);

        sink2.complete();
    }
}
