package me.nick.springbootlearn;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
@Order(1)
public class ValidateTokenFilter implements GlobalFilter {

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest requset = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        List<String> urls = new ArrayList<>();
        urls.add("/validateToken");
        urls.add("/login");
        String path = exchange.getRequest().getPath().value();
        if (!urls.contains(path)) {
            String token = exchange.getRequest().getHeaders().getFirst("token");

            return webClient.get()
                    .uri("lb://auth/validateToken")
                    .header("token", token)
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .flatMap(uid -> {
                        // 验证token如果成功，则把uid添加到headers或attributes中，后续filter或微服务可以直接出来用

                        // 添加到header
                        ServerWebExchange newExchange = exchange.mutate()
                                .request(exchange.getRequest().mutate().headers((headers) -> {
                                    headers.put("uid", List.of(String.valueOf(uid)));
                                }).build()).build();

                        // 添加到attributes
                        newExchange.getAttributes().put("uid", uid);

                        return chain.filter(newExchange);
                    })
                    .doOnError(e -> System.out.println("校验token失败: " + e.getMessage())) // 打印错误信息
                    .onErrorResume(e -> {
                        // 当出现错误时，返回“校验token失败”的响应，并且不执行后续的chain逻辑
                        response.setStatusCode(HttpStatus.UNAUTHORIZED); // 设置合适的HTTP状态码
                        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
                        byte[] bytes = "校验token失败".getBytes(StandardCharsets.UTF_8);
                        DataBuffer buffer = response.bufferFactory().wrap(bytes);
                        return response.writeWith(Mono.just(buffer));
                    });
        } else {
            return chain.filter(exchange);
        }
    }

}
