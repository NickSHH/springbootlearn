package me.nick.springbootlearn.webflux;

import java.io.IOException;

import reactor.core.publisher.Sinks;

public class ReactorDemo3 {

    public static void main(String[] args) throws IOException {
        //定义一个sink
        Sinks.Many<Integer> many = Sinks.many().unicast().onBackpressureBuffer();
        //绑定一个消费者
        many.asFlux().subscribe(System.out::println);
        many.tryEmitNext(1);
        many.tryEmitNext(2);
        many.tryEmitNext(3);
        many.tryEmitNext(4);

        System.in.read();
    }
}
