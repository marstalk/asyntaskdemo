package com.marstalk.asyntaskdemo.controller;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class AsyncTask implements Callable {
    private int rowCount;

    public AsyncTask(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public String call() {
        RestTemplate restTemplate = new RestTemplate();;
        String elapse = restTemplate.getForObject("http://localhost:8002/grid-service/async/grid/" + rowCount, String.class);
        System.out.println(elapse);
        return elapse;
    }
}
