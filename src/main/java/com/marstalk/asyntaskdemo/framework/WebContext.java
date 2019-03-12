package com.marstalk.asyntaskdemo.framework;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
public class WebContext {

    private HttpServletRequest request;

    public WebContext(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "WebContext{" +
                "request=" + request.getRequestURI() +
                '}';
    }
}
