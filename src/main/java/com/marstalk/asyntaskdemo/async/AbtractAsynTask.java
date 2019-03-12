package com.marstalk.asyntaskdemo.async;

import com.marstalk.asyntaskdemo.framework.WebContext;
import com.marstalk.asyntaskdemo.threadlocal.ThreadLocalHolder;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public abstract class AbtractAsynTask<T> implements Callable {

    protected AsyncService asyncService;
    protected CountDownLatch cdl;
    protected WebContext webContext;

    @Override
    public T call() throws Exception {
        beforeCall(webContext);
        T handle = handle();
        afterCall(webContext);
        return handle;
    }

    protected abstract T handle();

    protected void beforeCall(WebContext webContext) {
        ThreadLocalHolder.setWebContextThreadLocal(webContext);
    }

    protected void afterCall(WebContext webContext) {
        ThreadLocalHolder.setWebContextThreadLocal(null);
        cdl.countDown();
    }

}
