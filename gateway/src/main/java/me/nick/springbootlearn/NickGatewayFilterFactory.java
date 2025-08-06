package me.nick.springbootlearn;

import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class NickGatewayFilterFactory implements GatewayFilterFactory<NickGatewayFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {

            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                System.out.println(config.getPrefix());
                System.out.println("自定义过滤器");
                return chain.filter(exchange);
            }
            
        };
    }

    @Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList("prefix");
	}

    @Override
    public Class getConfigClass() {
        
        return Config.class;
    }
	public static class Config {

		private String prefix = "hello";

		public String getPrefix() {
			return prefix;
		}

		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}

	}
    
}
