package com.mrchen.myspring.bean.registry;

public interface SingletonBeanRegistry {
   Object getSingleton(String name);
   void registerSingleton(String name,Object bean);
}
