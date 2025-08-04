package me.nick.springbootlearn.webflux;

import java.io.IOException;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;


public class ReactorDemo {

    public static void main(String[] args) throws IOException {
        Flux<Integer> flux = Flux.just(1,2,3);
        flux.subscribe(new NickSubscriber());
        System.in.read();
    }

    static class NickSubscriber implements Subscriber<Integer> {

        private Subscription subscription;

        @Override
        public void onSubscribe(Subscription s) {
            this.subscription = s;
            s.request(1);
        }

        @Override
        public void onNext(Integer t) {
            System.out.println("消费者处理数据：" + t);
            subscription.request(1);

        }

        @Override
        public void onError(Throwable t) {
            System.out.println("消费异常");
        }

        @Override
        public void onComplete() {
            System.out.println("消费完成");
        }

        
    }
}
