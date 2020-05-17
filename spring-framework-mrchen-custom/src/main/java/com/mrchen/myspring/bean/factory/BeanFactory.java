package com.mrchen.myspring.bean.factory;

public interface BeanFactory {
    /**
     * 根据名称获取bean
      * @param name 类的名称
     * @return
     */
  Object getBean(String name);
}
