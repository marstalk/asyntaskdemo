package com.marstalk.asyntaskdemo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfiguration {

    @Bean("appExecutorService")
    public ExecutorService appExecutorService() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 50, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        return threadPoolExecutor;
    }

}
