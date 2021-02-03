package com.github.lgsxiaosen;

import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

import java.time.Duration;

/**
 * @author lgs
 * @date 2021/1/29
 **/
public class ReactorTest {

    public <T> Flux<T> appendBoomError(Flux<T> source) {
        return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
    }

    @Test
    public void testAppendBoomError() {
        Flux<String> source = Flux.just("foo", "bar");
        StepVerifier.create(
                appendBoomError(source))
                .expectNext("foo")
                .expectNext("bar")
                .expectErrorMessage("boom")
                .verify();
    }

    @Test
    public void timeTest(){
        StepVerifier.withVirtualTime(() -> Mono.delay(Duration.ofDays(1)))
                .expectSubscription()
                .expectNoEvent(Duration.ofDays(1))
                .expectNext(0L)
                .verifyComplete();
    }




}
