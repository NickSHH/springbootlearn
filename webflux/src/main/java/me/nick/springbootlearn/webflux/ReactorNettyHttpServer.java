package me.nick.springbootlearn.webflux;

import reactor.core.publisher.Flux;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class ReactorNettyHttpServer {
    public static void main(String[] args) {

        DisposableServer server = HttpServer.create()
                .host("localhost").port(8080)
                .handle((request, response) -> {
                    Flux<String> result = request.receive().asString().doOnNext(System.out::println)
                            .map(data -> processString(data));
                    return response.sendString(result);
                }).bindNow();
        server.onDispose().block();
    }
    private static String processString(String name) {
        return "Hello" + name;
    }
}