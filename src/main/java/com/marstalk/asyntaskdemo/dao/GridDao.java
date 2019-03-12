package com.marstalk.asyntaskdemo.dao;


import com.marstalk.asyntaskdemo.domain.Row;
import com.marstalk.asyntaskdemo.framework.WebContext;
import com.marstalk.asyntaskdemo.threadlocal.ThreadLocalHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GridDao {
    public void save(Row row, String userId) {

        try {
            Thread.sleep(600);
            int id = row.getId();
            String threadLocalId = ThreadLocalHolder.getThreadLocal();
            WebContext webContextThreadLocal = ThreadLocalHolder.getWebContextThreadLocal();
            String userIdFromThreadLocal = (String) webContextThreadLocal.getRequest().getAttribute("userId");
            if (!threadLocalId.equalsIgnoreCase(String.valueOf(id))) {
                System.err.println("********************************* row id do not equal ********************************");
            } else if (!userIdFromThreadLocal.equalsIgnoreCase(userId)) {
                System.err.println("********************************* userId do not equal ********************************");
            } else {
//                System.out.print(Thread.currentThread() + " rowId is: " + threadLocalId + " " + ThreadLocalHolder.getWebContextThreadLocal());
                System.out.print("#");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return;
    }
}
