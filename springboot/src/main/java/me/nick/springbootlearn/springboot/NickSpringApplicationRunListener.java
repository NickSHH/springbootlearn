package me.nick.springbootlearn.springboot;

import java.time.Duration;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class NickSpringApplicationRunListener implements SpringApplicationRunListener {

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("启动中");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext,
            ConfigurableEnvironment environment) {
        System.out.println("Environment对象准备好了");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("Spring容器创建好了");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("Spring容器设置完了配置类");
    }
    
    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("Spring容器刷新完了，扫描了");
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("callRunner执行完了");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("Spring启动失败");
    }

}
