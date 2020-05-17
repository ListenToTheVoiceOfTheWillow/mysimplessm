package com.mrchen.myspring.bean.factory;

import com.mrchen.myspring.bean.beandefinition.BeanDefinition;

public interface AutowireCapableBeanFactory {
    /**
     * 创建bean实例
      * @return
     */
    Object  createBean(BeanDefinition beanDefinition);
}
