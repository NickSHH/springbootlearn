package me.nick.springbootlearn.webflux;

import java.io.IOException;
import java.util.concurrent.Executors;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

public class ReactorDemo2 {

    public static void main(String[] args) throws IOException {
        System.out.println("Reactor Demo 2");

        Flux<String> flux = Flux.just("bei", "jing", "tong", "ren", "tang");
        ParallelFlux<Object> result = flux.parallel().runOn(Schedulers.fromExecutor(Executors.newFixedThreadPool(5)))
        .flatMap(name -> {
            Mono<String> prefixNameMono = prefixName(name);
            Mono<Integer> nameLengthMono = nameLength(name);
            return prefixNameMono.zipWith(nameLengthMono, (prefixName, length) -> prefixName + "'s length is "+length);
        });
        result.subscribe(System.out::println);
        System.in.read();
    }

    private static Mono<String> prefixName(String name) {
        System.out.println(name + " prefixName's thread: " + Thread.currentThread().getName());
        return Mono.just("name:" + name);
    }

    private static Mono<Integer> nameLength(String name) {
        System.out.println(name + "nameLength's thread: " + Thread.currentThread().getName());
        return Mono.just(name.length());
    }
}
