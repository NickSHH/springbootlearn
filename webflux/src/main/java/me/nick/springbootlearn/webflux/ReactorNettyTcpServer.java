package me.nick.springbootlearn.webflux;

import java.io.IOException;

import reactor.core.publisher.Flux;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class ReactorNettyTcpServer {
    public static void main(String[] args) throws IOException {
        DisposableServer server = TcpServer.create().handle((inbound, outbound) -> {
            Flux<String> result = inbound.receive().asString().doOnNext(System.out::println).map(data -> "hello: "+data);
            return outbound.sendString(result);
        })
        .host("127.0.0.1").port(8080).bindNow();
        server.onDispose().block();
    }
}
