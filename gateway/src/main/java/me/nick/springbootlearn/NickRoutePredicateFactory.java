package me.nick.springbootlearn;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.RoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class NickRoutePredicateFactory implements RoutePredicateFactory<NickRoutePredicateFactory.config> {

    public static class config {
        private String method;

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }
    }


    @Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList("method");
	}

    @Override
    public Predicate<ServerWebExchange> apply(config config) {
        return new Predicate<ServerWebExchange>() {

            @Override
            public boolean test(ServerWebExchange arg0) {
                return arg0.getRequest().getMethod().name().equals("GET");
            }
            
        };
    }

    @Override
    public Class<config> getConfigClass() {
        return config.class;
    }

    

}
