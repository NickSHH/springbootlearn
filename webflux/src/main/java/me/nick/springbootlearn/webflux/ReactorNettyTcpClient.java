package me.nick.springbootlearn.webflux;

import java.io.IOException;

import reactor.core.publisher.Flux;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

public class ReactorNettyTcpClient {

    public static void main(String[] args) throws IOException {
        Connection connection = TcpClient.create().host("127.0.0.1").port(8080).connectNow();
        connection.outbound().sendString(Flux.just("nick")).then().subscribe();

        connection.inbound().receive().asString().doOnNext(System.out::println).then().subscribe();
        
        connection.onDispose().block();
    }
}
