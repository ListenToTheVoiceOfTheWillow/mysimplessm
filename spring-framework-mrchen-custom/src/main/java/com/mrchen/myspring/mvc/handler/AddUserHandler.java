package com.mrchen.myspring.mvc.handler;

import com.mrchen.myspring.mvc.handler.iface.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
          String id=request.getParameter("id");
          response.setContentType("text/plain;charset=utf8");
          response.getWriter().write("成功+"+id);
    }
}
