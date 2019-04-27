package com.github.sioncheng.rx.basic;

import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

public class Operators {

    public static void main(String[] args) {
        //filter
        Flux.fromArray(new Integer[]{1,2,3,4,5,6,7,8,9}).filter(e -> e % 2 == 0).subscribe(System.out::println);
        //tak
        Flux.fromStream(IntStream.range(0, 100).boxed()).take(1).subscribe(System.out::println);
        //skip
        Flux.fromStream(IntStream.range(0, 100).boxed()).skip(1).take(1).subscribe(System.out::println);
        //map
        Flux.fromStream(IntStream.range(0, 100).boxed()).take(1)
                .map(x -> Integer.toString(x) + "d").subscribe(System.out::println);
        //concatWith
        Flux.fromArray(new Integer[]{1,2,3}).concatWith(Flux.fromStream(IntStream.range(4,6).boxed()))
                .subscribe(System.out::println);
    }
}
