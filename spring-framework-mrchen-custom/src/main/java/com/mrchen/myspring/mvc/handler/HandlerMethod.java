package com.mrchen.myspring.mvc.handler;

import java.lang.reflect.Method;

public class HandlerMethod {
    private Object controller;
    private Method method;

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public HandlerMethod(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }
}
