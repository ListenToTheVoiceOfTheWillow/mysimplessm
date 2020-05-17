package com.mrchen.myspring.mvc.servlet;


import com.mrchen.myspring.bean.factory.support.DefaultListableBeanFactory;
import com.mrchen.myspring.bean.reader.XmlBeanDefinitionReader;
import com.mrchen.myspring.bean.reader.support.XmlBeanDefinitionReaderImpl;
import com.mrchen.myspring.bean.xml.ClasspathResource;
import com.mrchen.myspring.bean.xml.Resource;

import com.mrchen.myspring.mvc.handleradapter.iface.HandlerAdapter;
import com.mrchen.myspring.mvc.handlermapping.iface.HandlerMapping;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet extends AbstractServlet {


    private static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private DefaultListableBeanFactory beanFactory;

    /**
     * handlerMapping集合
     */
    private List<HandlerMapping> handlerMappings=new ArrayList<>();

    /**
     * handlerAdapter集合
     */
    private List<HandlerAdapter> handlerAdapters=new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        String location=config.getInitParameter(CONTEXT_CONFIG_LOCATION);
        initSpringContainer(location);
        initHandlerMappingList();
        initHandlerAdapterList();
    }

    private void initSpringContainer(String location) {
        beanFactory=new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReaderImpl(beanFactory);
        Resource resource=new ClasspathResource(location);
        InputStream inputStream=resource.getResource();
        reader.loadBeanDefinitions(inputStream);
    }

    /**
     * 初始化handlerAdapters
     */
    private void initHandlerAdapterList() {
        handlerAdapters=beanFactory.getBeansByType(HandlerAdapter.class);
    }

    /**
     * 初始化handlerMappings
     */
    private void initHandlerMappingList() {
        handlerMappings=beanFactory.getBeansByType(HandlerMapping.class);
    }

    /**
     *分发请求
     * @param request
     * @param response
     */
    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) {
       //根据请求查找处理器
       Object handler=getHandler(request);
        if (handler==null){
            return;
        }
       //根据处理器查找相应的处理器适配器
        HandlerAdapter handlerAdapter=getHandlerAdapter(handler);
        if (handlerAdapter==null){
            return;
        }
       //统一调用不同类型的处理器
        try {
            handlerAdapter.handleRequest(handler,request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }



    /**
     * 根据请求查找handler
     * @param request
     * @return
     */
    private Object getHandler(HttpServletRequest request) {
        Object handler=null;
        for (HandlerMapping handlerMapping:handlerMappings){
             handler=handlerMapping.getHandler(request);
             if (handler!=null){
                 return handler;
             }
        }
        return null;
    }

    /**
     * 根据handler 找到对应的handlerAdapter
     * @param handler
     * @return
     */
    private HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter handlerAdapter:handlerAdapters){
            if (handlerAdapter.supports(handler)){
                return handlerAdapter;
            }
        }
        return null;
    }
}
