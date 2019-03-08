package com.marstalk.asyntaskdemo.dao;


import com.marstalk.asyntaskdemo.threadlocal.ThreadLocalHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GridDao {

    public void save() {
        System.out.println(Thread.currentThread() + " id is: " + ThreadLocalHolder.getThreadLocal());
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return;
    }
}
