package com.mrchen.myspring.bean.aware.iface;


import com.mrchen.myspring.bean.factory.BeanFactory;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory);
}
