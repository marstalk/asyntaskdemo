package com.marstalk.asyntaskdemo.async;


import com.marstalk.asyntaskdemo.domain.Grid;
import com.marstalk.asyntaskdemo.service.CrudServiceSaveGridTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class QgenAsynService {

    @Autowired
    @Qualifier(value = "appExecutorService")
    private ExecutorService executorService;

    public void submit(AsyncService crudService, CrudServiceSaveGridTask crudServiceSaveGridTask, List<Grid> split) {
        for (Grid grid : split) {
            executorService.submit(crudServiceSaveGridTask);
        }
    }
}
