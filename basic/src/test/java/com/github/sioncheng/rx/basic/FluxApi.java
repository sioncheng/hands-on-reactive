package com.github.sioncheng.rx.basic;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FluxApi {

    public static void main(String[] args) {
        Flux.just("a","b","c").subscribe(System.out::println);

        Flux.fromIterable(Arrays.asList(1,2,3)).subscribe(System.out::println);

        Flux.fromStream(IntStream.range(1, 10).boxed()).subscribe(System.out::println);

        Flux.empty().subscribe(System.out::println);

        Flux.error(new RuntimeException("hahahh")).subscribe(System.out::println);
    }
}
