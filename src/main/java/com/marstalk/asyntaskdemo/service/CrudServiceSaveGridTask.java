package com.marstalk.asyntaskdemo.service;

import com.marstalk.asyntaskdemo.async.AbtractAsynTask;
import com.marstalk.asyntaskdemo.async.AsyncService;
import com.marstalk.asyntaskdemo.domain.Grid;
import com.marstalk.asyntaskdemo.framework.WebContext;

import java.util.concurrent.CountDownLatch;

public class CrudServiceSaveGridTask extends AbtractAsynTask<Grid> {
    private Grid grid;
    private String userId;

    public CrudServiceSaveGridTask(AsyncService asyncService, Grid grid, WebContext webContext, CountDownLatch cdl, String userId) {
        this.grid = grid;
        this.asyncService = asyncService;
        this.webContext = webContext;

        this.userId = userId;
        this.cdl = cdl;
    }

    @Override
    protected Grid handle() {
        CrudService crudService = (CrudService) this.asyncService;
        Grid grid = crudService.save(this.grid, this.webContext, this.userId);
        return grid;
    }
}
