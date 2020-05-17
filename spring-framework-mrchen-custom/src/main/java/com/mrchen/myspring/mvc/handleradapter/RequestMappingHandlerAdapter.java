package com.mrchen.myspring.mvc.handleradapter;

import com.mrchen.myspring.mvc.annotation.ResponseBody;
import com.mrchen.myspring.mvc.handler.HandlerMethod;
import com.mrchen.myspring.mvc.handleradapter.iface.HandlerAdapter;
import com.mrchen.myspring.mvc.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: myspringmvc1
 * @description: 将RequestMapping注解的HandlerMethod适配
 * @author: mrchen
 * @create: 2020-01-17 15:05
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        if (handler instanceof HandlerMethod){
            return true;
        }
        return false;
    }

    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
       HandlerMethod handlerMethod= (HandlerMethod) handler;
       Object controller=handlerMethod.getController();
        Method method=handlerMethod.getMethod();
        //处理参数
        Object[] args=resolveParameter(request,method);
        //反射调用controller类中的方法
        Object returnValue=method.invoke(controller,args);
        //处理返回值
        handleReturnValue(returnValue,method,response);
    }

    private void handleReturnValue(Object returnValue, Method method, HttpServletResponse response) throws IOException {
      //处理返回值
        if (method.isAnnotationPresent(ResponseBody.class)){
           //数据的处理
            if (returnValue instanceof String) {
                response.setContentType("text/plain;charset=utf8");
                response.getWriter().write((String) returnValue);
            }else if (returnValue instanceof Map){
                 response.setContentType("application/json;charset=utf8");
                 response.getWriter().write(JsonUtils.object2Json(returnValue));
            }
            //else其他类型
        }else {
            //视图的处理
        }
    }

    private Object[] resolveParameter(HttpServletRequest request, Method method) {
        List<Object> args=new ArrayList<>();
        //获取请求中的参数
        Map<String,String[]> parameterMap=request.getParameterMap();
        //方法需要的参数
        Parameter[] parameters=method.getParameters();

        for (Parameter parameter:parameters){
          String name=parameter.getName();
          Class<?> type=parameter.getType();
          String[] stringValue=parameterMap.get(name);
          Object valueToUse=convertValue(stringValue,type);
          args.add(valueToUse);
        }
          return args.toArray();


    }

    private Object convertValue(String[] stringValue, Class<?> type) {
      //TODO
        Object valueToUse=null;
       if (type == Integer.class){
           valueToUse=Integer.parseInt(stringValue[0]);
        }else if (type== String.class){
         valueToUse= stringValue[0];
       }
       //else ...各种各样的类
       return valueToUse;
    }

}
