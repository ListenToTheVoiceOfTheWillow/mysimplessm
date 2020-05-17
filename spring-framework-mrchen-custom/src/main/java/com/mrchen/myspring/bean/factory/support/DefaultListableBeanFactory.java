package com.mrchen.myspring.bean.factory.support;

import com.mrchen.myspring.bean.beandefinition.BeanDefinition;
import com.mrchen.myspring.bean.factory.ListableBeanFactory;
import com.mrchen.myspring.bean.registry.BeanDefinitionRegistry;


import java.util.*;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ListableBeanFactory , BeanDefinitionRegistry {

    private Map<String,BeanDefinition> beanDefinitions=new HashMap<>();

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitions.put(name,beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return beanDefinitions.get(name);
    }

    @Override
    public List<BeanDefinition> getBeanDefinitions() {
        List<BeanDefinition> definitions=new ArrayList<>();
        Collection<BeanDefinition> collection=beanDefinitions.values();
        for (BeanDefinition beanDefinition:collection){
            definitions.add(beanDefinition);
        }
        return definitions;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getBeansByType(Class<?> type) {
        List<T> beans = new ArrayList<T>();
        try {
            for (BeanDefinition bd : beanDefinitions.values()) {
                String clazzName = bd.getClassName();
                // 获取BeanDefinition对应的class的类对象
                Class<?> clazz = Class.forName(clazzName);
                // 如果type是clazz的父类或者是当前类，则返回true
                if (type.isAssignableFrom(clazz)) {
                    Object bean = getBean(bd.getBeanName());
                    beans.add((T) bean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beans;
    }

    @Override
    public List<String> getBeanNamesByType(Class<?> type) {
        return null;
    }
}
