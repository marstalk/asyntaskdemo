package com.marstalk.asyntaskdemo.service;


import com.marstalk.asyntaskdemo.async.AsyncService;
import com.marstalk.asyntaskdemo.dao.GridDao;
import com.marstalk.asyntaskdemo.domain.Grid;
import com.marstalk.asyntaskdemo.domain.Row;
import com.marstalk.asyntaskdemo.framework.WebContext;
import com.marstalk.asyntaskdemo.threadlocal.ThreadLocalHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudService implements AsyncService {
    @Autowired
    private GridDao gridDao;

    public Grid save(Grid grid, WebContext webContext, String userId) {

        for (Row row : grid.getRows()) {
            ThreadLocalHolder.setThreadLocal(String.valueOf(row.getId()));
            gridDao.save(row, userId);
        }
        return grid;
    }
}
