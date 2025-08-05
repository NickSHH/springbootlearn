package me.nick.springbootlearn.webflux;

import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class NickController {

    @GetMapping("/test")
    public String test() {
        return "Hello world";
    }

    @GetMapping("/testMono")
    public Mono<String> testMono() {
        return Mono.just("Hello Mono");
    }

    @GetMapping("/testFlux")
    public Flux<String> testFlux() {
        return Flux.just("hello flux1", "hello flux2");
    }

    @GetMapping(value = "/testSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> testSSE() {
        return Flux.just("hello flux1", "hello flux2").delayElements(Duration.ofSeconds(1));
    }

}
