package com.mrchen.myspring.bean.registry.support;

import com.mrchen.myspring.bean.beandefinition.BeanDefinition;

import com.mrchen.myspring.bean.registry.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultBeanDefinitionRegistry implements BeanDefinitionRegistry {

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return null;
    }

    @Override
    public List<BeanDefinition> getBeanDefinitions() {
        return null;
    }
}
