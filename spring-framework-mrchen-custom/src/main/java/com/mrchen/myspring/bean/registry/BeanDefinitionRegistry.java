package com.mrchen.myspring.bean.registry;

import com.mrchen.myspring.bean.beandefinition.BeanDefinition;

import java.util.List;

public interface BeanDefinitionRegistry {
    void  registerBeanDefinition(String name,BeanDefinition beanDefinition);
    BeanDefinition getBeanDefinition(String name);
    List<BeanDefinition> getBeanDefinitions();
}
