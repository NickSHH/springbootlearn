package me.nick.springbootlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
 
import static org.springframework.web.servlet.function.RouterFunctions.route;

@SpringBootApplication
public class UserApplication {
 
    @Bean
    public RouterFunction<ServerResponse> user() {
        return route()
                .GET("/test", request -> {
                    return ServerResponse.status(HttpStatus.OK).body("get user success!");
                })
                .build();
    }
 
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}