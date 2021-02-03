package com.github.lgsxiaosen;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lgs
 * @date 2021/1/27
 **/
public class GenerateMain {

    public static void main(String[] args){
        System.out.println("-------------");
//        generate1();
//        generate2();
        generate3();
        System.out.println("-------------");
    }

    private static void generate1(){
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3*state);
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 1;
                });
        flux.subscribe(System.out::println);
    }

    private static void generate2(){
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3*i);
                    if (i == 10) {
                        sink.complete();
                    }
                    return state;
                });
        flux.subscribe(System.out::println);
    }

    private static void generate3(){
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3*i);
                    if (i == 10) {
                        sink.complete();
                    }
                    return state;
                }, (state) -> System.out.println("state: " + state));
        flux.subscribe(System.out::println);
    }

}
