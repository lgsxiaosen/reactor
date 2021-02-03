package com.github.lgsxiaosen;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * @author lgs
 * @date 2021/1/27
 **/
public class SampleSubscriber<T> extends BaseSubscriber<T> {
    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println("SampleSubscriber: " + value);
        request(1);
    }

    @Override
    protected void hookOnComplete() {
        System.out.println("SampleSubscriber Done" );
    }
}
