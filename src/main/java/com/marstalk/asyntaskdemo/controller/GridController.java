package com.marstalk.asyntaskdemo.controller;


import com.marstalk.asyntaskdemo.async.QgenAsynService;
import com.marstalk.asyntaskdemo.domain.Grid;
import com.marstalk.asyntaskdemo.framework.WebContext;
import com.marstalk.asyntaskdemo.service.CrudService;
import com.marstalk.asyntaskdemo.service.CrudServiceSaveGridTask;
import com.marstalk.asyntaskdemo.service.SaveGridSplitStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RestController
public class GridController {

    @Autowired
    private CrudService crudService;
    @Autowired
    private SaveGridSplitStrategy saveGridSplitStrategy;
    @Autowired
    private QgenAsynService asynService;
    @Autowired
    @Qualifier(value = "appExecutorService")
    private ExecutorService executorService;

    @GetMapping("/grid/{count}")
    public String save(@PathVariable int count, HttpServletRequest request) {
        long begin = System.currentTimeMillis();

        String userId = UUID.randomUUID().toString();

        Grid grid = Grid.instance(count);
        WebContext webContext = new WebContext(request);
        crudService.save(grid, webContext, userId);

        return (System.currentTimeMillis() - begin) / 1000 + "s";
    }


    @GetMapping("/async/grid/{count}")
    public String asynSave(@PathVariable int count, HttpServletRequest request) {
        long begin = System.currentTimeMillis();
        String userId = UUID.randomUUID().toString();
        request.setAttribute("userId", userId);

        WebContext webContext = new WebContext(request);
        Grid grid = Grid.instance(count);

        List<Grid> split = saveGridSplitStrategy.split(grid);
        CountDownLatch countDownLatch = new CountDownLatch(split.size());

        List<Future<Grid>> futures = new ArrayList<>();
        for (Grid splitedGrid : split) {
            Future future = executorService.submit(new CrudServiceSaveGridTask(crudService, splitedGrid, webContext, countDownLatch, userId));
            futures.add(future);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Grid> grids = new ArrayList<>();
        for (Future<Grid> future : futures) {
            try {
                grids.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        Grid combinedGrid = saveGridSplitStrategy.combine(grids);

        System.out.println(combinedGrid);

        return (System.currentTimeMillis() - begin) / 1000 + "s";
    }

}
