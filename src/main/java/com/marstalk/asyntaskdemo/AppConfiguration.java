package com.marstalk.asyntaskdemo;


import com.marstalk.asyntaskdemo.async.QgenThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfiguration {

    @Bean("appExecutorService")
    public ExecutorService appExecutorService() {
        QgenThreadPoolExecutor threadPoolExecutor = new QgenThreadPoolExecutor(50, 50, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        return threadPoolExecutor;
    }

}
