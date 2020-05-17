package com.mrchen.myspring.mvc.handleradapter.iface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface HandlerAdapter {
     //提供一个适配方法
     boolean supports(Object handler);

     void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException;
}
