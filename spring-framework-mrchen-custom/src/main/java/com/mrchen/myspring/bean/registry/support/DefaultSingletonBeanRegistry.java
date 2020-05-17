package com.mrchen.myspring.bean.registry.support;

import com.mrchen.myspring.bean.registry.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{
    private Map<String,Object> singletons=new HashMap<>();
    @Override
    public Object getSingleton(String name) {
        return singletons.get(name);
    }

    @Override
    public void registerSingleton(String name, Object bean) {
       singletons.put(name,bean);
    }
}
