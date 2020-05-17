package com.mrchen.myspring.mvc.handlermapping.iface;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    Object getHandler(HttpServletRequest request);
}
