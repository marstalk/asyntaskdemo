package com.marstalk.asyntaskdemo.threadlocal;

import com.marstalk.asyntaskdemo.framework.WebContext;

public class ThreadLocalHolder {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    private static ThreadLocal<WebContext> webContextThreadLocal = new ThreadLocal<>();


    public static String getThreadLocal() {
        return threadLocal.get();
    }

    public static void setThreadLocal(String str) {
        threadLocal.set(str);
    }

    public static String getInheritableThreadLocal() {
        return inheritableThreadLocal.get();
    }

    public static void setInheritableThreadLocal(String str) {
        inheritableThreadLocal.set(str);
    }

    public static WebContext getWebContextThreadLocal() {
        return webContextThreadLocal.get();
    }

    public static void setWebContextThreadLocal(WebContext webContext) {
        webContextThreadLocal.set(webContext);
    }
}
