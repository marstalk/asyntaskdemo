package com.marstalk.asyntaskdemo.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GridControllerTests {
    private static int rowCount = 5000;


    /**
     *  simulate one user saves grid
     */
    @Test
    public void syncTest() {
        //sync task
        RestTemplate restTemplate = new RestTemplate();
        SyncTask syncTask = new SyncTask(rowCount, restTemplate);
        syncTask.run();
    }

    /**
     * simulate multiple users save grid asynchronously
     */
    @Test
    public void asyncTest() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());

        int usersCount = 1;

        ArrayList<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < usersCount; i++) {
            AsyncTask asyncTask = new AsyncTask(rowCount);
            Future<String> future = threadPoolExecutor.submit(asyncTask);
            futures.add(future);
        }

        try {
            System.out.println("main thread is waiting...");
            threadPoolExecutor.awaitTermination(5, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Future future : futures) {
            System.out.println(rowCount + " async spend " + future.get());
        }

        System.out.println(usersCount + " users saved " + rowCount + "separately and asynchronously");
    }
}







