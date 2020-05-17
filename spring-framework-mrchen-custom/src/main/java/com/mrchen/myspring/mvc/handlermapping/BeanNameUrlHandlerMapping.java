package com.mrchen.myspring.mvc.handlermapping;

import com.mrchen.myspring.bean.aware.iface.BeanFactoryAware;
import com.mrchen.myspring.bean.beandefinition.BeanDefinition;
import com.mrchen.myspring.bean.factory.BeanFactory;
import com.mrchen.myspring.bean.factory.support.DefaultListableBeanFactory;

import com.mrchen.myspring.mvc.handlermapping.iface.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanNameUrlHandlerMapping implements HandlerMapping , BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;
    private Map<String,Object> urlHandler=new HashMap<>();

    public void init(){
        List<BeanDefinition> definitions=beanFactory.getBeanDefinitions();
        //取出BeanDefinition对象中的beanName进行处理，如果是uri则获取该beanDefinition对应的实例
        for (BeanDefinition definition:definitions){
          String beanName=  definition.getBeanName();
          if (beanName!=null&&beanName.startsWith("/")){
              urlHandler.put(beanName,beanFactory.getBean(beanName));
          }
        }


    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory= (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String uri=request.getRequestURI();
        Object handler=urlHandler.get(uri);
        return handler;
    }
}
