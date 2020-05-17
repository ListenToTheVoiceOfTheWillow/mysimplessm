package com.mrchen.myspring.mvc.handleradapter;

import com.mrchen.myspring.mvc.handleradapter.iface.HandlerAdapter;
import com.mrchen.myspring.mvc.handler.iface.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpRequestHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        if (handler instanceof HttpRequestHandler){
            return true;
        }
        return false;

    }

    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ((HttpRequestHandler)handler).handleRequest(request,response);
    }
}
