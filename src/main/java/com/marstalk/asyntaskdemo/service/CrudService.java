package com.marstalk.asyntaskdemo.service;


import com.marstalk.asyntaskdemo.dao.GridDao;
import com.marstalk.asyntaskdemo.domain.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudService {
    @Autowired
    private GridDao gridDao;

    public Grid save() {
        gridDao.save();
        return new Grid();
    }
}
