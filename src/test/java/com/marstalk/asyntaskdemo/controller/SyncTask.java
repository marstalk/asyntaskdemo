package com.marstalk.asyntaskdemo.controller;

import org.springframework.web.client.RestTemplate;

public class SyncTask implements Runnable {
    private int rowCount;
    private RestTemplate restTemplate;

    public SyncTask(int rowCount, RestTemplate restTemplate) {
        this.rowCount = rowCount;
        this.restTemplate = restTemplate;
    }

    @Override
    public void run() {
        String elapse = restTemplate.getForObject("http://localhost:8002/grid-service/grid/" + rowCount, String.class);
        System.out.println(rowCount + " sync spend " + elapse);
    }
}