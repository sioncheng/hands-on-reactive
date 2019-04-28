package com.github.sioncheng.rx.basic;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.IdentityHashMap;

public class FibonacciPublisher implements Publisher<Integer> {


    private IdentityHashMap<Subscriber<? super  Integer>, Integer> subscribers =
            new IdentityHashMap<>();

    class FibonacciSubscription implements Subscription {

        private Subscriber<? super Integer> subscriber;

        public FibonacciSubscription(Subscriber<? super Integer> subscriber) {
            this.subscriber = subscriber;
        }
        @Override
        public void request(long l) {

            int a = 0;
            int b = 1;
            subscriber.onNext(a);
            subscriber.onNext(b);

            for (int i = 2; i < l; i++) {
                int c = a + b;
                subscriber.onNext(c);
                a = b;
                b = c;
            }

            subscriber.onComplete();
        }

        @Override
        public void cancel() {
            subscribers.remove(subscriber);
        }

    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        if (subscribers.putIfAbsent(subscriber, 0) == null) {
            subscriber.onSubscribe(new FibonacciSubscription(subscriber));
        }
    }

    public static void main(String[] args) {
        FibonacciPublisher fibonacciPublisher =new FibonacciPublisher();

        fibonacciPublisher.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe");
                subscription.request(5);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });


    }
}
