package com.mrchen.myspring.mvc.handlermapping;

import com.mrchen.myspring.bean.aware.iface.BeanFactoryAware;
import com.mrchen.myspring.bean.beandefinition.BeanDefinition;
import com.mrchen.myspring.bean.factory.BeanFactory;
import com.mrchen.myspring.bean.factory.support.DefaultListableBeanFactory;
import com.mrchen.myspring.mvc.annotation.RequestMapping;
import com.mrchen.myspring.mvc.handlermapping.iface.HandlerMapping;
import com.mrchen.myspring.mvc.annotation.Controller;
import com.mrchen.myspring.mvc.handler.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *专门针对注解方式的处理器，建立映射关系
 */
public class RequestMappingHandlerMapping implements HandlerMapping, BeanFactoryAware {

    private Map<String, HandlerMethod> urlHandlerMethods=new HashMap<>();

    private DefaultListableBeanFactory beanFactory;

    /**
     * 初始化时候  初始化urlHandlerMethods
     */
    public void init(){
      List<BeanDefinition> definitions=beanFactory.getBeanDefinitions();
      for (BeanDefinition definition:definitions){
         String clazzName=definition.getClassName();
          Class<?> clazz=getClazz(clazzName);
          // 判断该BeanDefinition中的class是否带有@Controller或者@RequestMapping注解
          if (isHandler(clazz)){
             Method[] methods= clazz.getDeclaredMethods();
             for (Method method:methods){
                 if (method.isAnnotationPresent(RequestMapping.class)){
                     StringBuffer stringBuffer=new StringBuffer();
                     RequestMapping requestMappingClazz=clazz.getAnnotation(RequestMapping.class);
                     if (requestMappingClazz!=null && requestMappingClazz.value()!=null &&  !"".equals(requestMappingClazz)){
                         stringBuffer.append("/");
                         stringBuffer.append(requestMappingClazz.value());
                     }
                     RequestMapping requestMappingMethod=method.getAnnotation(RequestMapping.class);
                     if (requestMappingMethod!=null && requestMappingMethod.value()!=null&& !"".equals(requestMappingMethod.value())){
                         stringBuffer.append("/");
                         stringBuffer.append(requestMappingMethod.value());
                     }
                   Object controller=beanFactory.getBean(definition.getBeanName());
                   HandlerMethod handlerMethod=new HandlerMethod(controller,method);
                   if (stringBuffer.toString()==null||"".equals(stringBuffer.toString())){
                       return;
                   }
                     urlHandlerMethods.put(stringBuffer.toString(),handlerMethod);
                 }


             }
          }
      }


    }

    private boolean isHandler(Class<?> clazz) {
          return clazz.isAnnotationPresent(Controller.class)||clazz.isAnnotationPresent(RequestMapping.class);
    }

    private Class<?> getClazz(String clazzName) {
        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String uri=request.getRequestURI();
        Object handler=urlHandlerMethods.get(uri);
        return handler;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory= (DefaultListableBeanFactory) beanFactory;
    }
}
