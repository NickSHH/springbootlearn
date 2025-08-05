package me.nick.springbootlearn.webflux;

import java.io.IOException;
import java.util.concurrent.Executors;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


public class ReactorDemo {

    public static void main(String[] args) throws IOException {
        Flux<Integer> flux = Flux.just(1,2,3,4).map(integer -> {
            System.out.println("生产者：" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return integer;
        }).publishOn(Schedulers.fromExecutor(Executors.newFixedThreadPool(5)));
        System.out.println("subscribe before");
        flux.subscribe(new NickSubscriber());
        System.out.println("subscribe after");
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
            System.out.println("消费者处理数据：" + Thread.currentThread().getName() + "，数据为：" + t);
            // if (t > 2) {
            //     subscription.cancel();
            // } else {
            //     subscription.request(1);
            // }
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
