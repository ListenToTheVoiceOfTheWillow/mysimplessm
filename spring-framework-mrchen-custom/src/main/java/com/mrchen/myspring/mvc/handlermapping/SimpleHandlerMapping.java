package com.mrchen.myspring.mvc.handlermapping;


import com.mrchen.myspring.mvc.handler.AddUserHandler;
import com.mrchen.myspring.mvc.handler.GetUserHandler;
import com.mrchen.myspring.mvc.handlermapping.iface.HandlerMapping;


import javax.servlet.http.HttpServletRequest;

public class SimpleHandlerMapping implements HandlerMapping {

    @Override
    public Object getHandler(HttpServletRequest request) {
        String uri=request.getRequestURI();
        if (uri.equals("/add")){
            return new AddUserHandler();
        }
        if (uri.equals("/get")){
            return new GetUserHandler();
        }
        return null;
    }
}
