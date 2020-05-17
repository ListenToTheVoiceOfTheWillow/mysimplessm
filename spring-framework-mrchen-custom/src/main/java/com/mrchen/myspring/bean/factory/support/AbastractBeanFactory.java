package com.mrchen.myspring.bean.factory.support;

import com.mrchen.myspring.bean.beandefinition.BeanDefinition;
import com.mrchen.myspring.bean.factory.BeanFactory;
import com.mrchen.myspring.bean.registry.SingletonBeanRegistry;
import com.mrchen.myspring.bean.registry.support.DefaultSingletonBeanRegistry;

public abstract class AbastractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory{


    @Override
    public Object getBean(String name) {
        //从 SingletonBeanRegistry中获取实例
       Object bean= getSingleton(name);
       if (bean!=null){
           return bean;
       }
       //如果没有则自己创建实例
        BeanDefinition beanDefinition=getBeanDefinition(name);
        bean=createBean(beanDefinition);

       //把实例放进singletonbeanregistry中
       registerSingleton(name,bean);
        return bean;
    }

    protected abstract Object createBean(BeanDefinition beanDefinition);

    protected abstract BeanDefinition getBeanDefinition(String name);


}
