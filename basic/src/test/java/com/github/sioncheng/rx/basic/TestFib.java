package com.github.sioncheng.rx.basic;

import org.junit.Assert;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.util.ArrayList;
import java.util.List;

public class TestFib {

    @Test
    public void testFib() {
        //中文
        Flux<Long> fibonacciGenerator = Flux.generate(
                ()-> Tuples.of(0L, 1L),
                (state, sink) -> {
                    sink.next(state.getT1());
                    return Tuples.of(state.getT2(), state.getT1() + state.getT2());
                }

        );

        Assert.assertNotNull(fibonacciGenerator);

        List<Long> fibonacciNumbers = new ArrayList<>();
        fibonacciGenerator.take(10).subscribe(x -> fibonacciNumbers.add(x));

        Assert.assertEquals(10, fibonacciNumbers.size());
        Assert.assertEquals(0, fibonacciNumbers.get(0).intValue());
        Assert.assertEquals(1, fibonacciNumbers.get(1).intValue());
        Assert.assertEquals(1, fibonacciNumbers.get(2).intValue());
        Assert.assertEquals(2, fibonacciNumbers.get(3).intValue());
        Assert.assertEquals(3, fibonacciNumbers.get(4).intValue());
        Assert.assertEquals(5, fibonacciNumbers.get(5).intValue());
        Assert.assertEquals(8, fibonacciNumbers.get(6).intValue());
    }
}
