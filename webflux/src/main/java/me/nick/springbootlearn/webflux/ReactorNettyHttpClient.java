package me.nick.springbootlearn.webflux;

import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;

public class ReactorNettyHttpClient {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.create().host("localhost").port(8080);
        String result = httpClient.post().send(ByteBufFlux.fromString(Mono.just("Nick"))).responseContent().asString().next().block();
        System.out.println(result);
    }

}
