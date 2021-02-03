package com.github.lgsxiaosen;

import reactor.core.publisher.Flux;

/**
 * @author lgs
 * @date 2021/1/27
 **/
public class SubscribeMain {

    public static void main(String[] args){
        System.out.println("----------------");
//        subscribe1();
//        subscribe2();
//        subscribe3();
//        subscribe4();
        subscribe5();
        System.out.println("----------------");
    }

    /**
     * 无参订阅
     */
    private static void subscribe1(){
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe();
    }

    /**
     * 订阅一个消费者
     */
    private static void subscribe2(){
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(System.out::println);
    }

    /**
     * 订阅两个参数，消费者、异常
     */
    private static void subscribe3(){
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) {
                        return i;
                    }
                    throw new RuntimeException("Got to 4");
                });
        ints.subscribe(System.out::println,
                error -> System.err.println("Error: " + error));
    }

    /**
     * 订阅三个参数，消费者、异常、结束方法
     */
    private static void subscribe4(){
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(System.out::println,
                error -> System.err.println("Error " + error),
                () -> {System.out.println("Done");});
    }

    private static void subscribe5(){
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(System.out::println,
                error -> System.err.println("Error " + error),
                () -> {System.out.println("Done");},
                s -> ss.request(10));
        ints.subscribe(ss);
    }


}
