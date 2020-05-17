package com.mrchen.myspring.mvc.handler.iface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpRequestHandler {
   void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
