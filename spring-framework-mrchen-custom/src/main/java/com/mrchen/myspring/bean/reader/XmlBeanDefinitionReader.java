package com.mrchen.myspring.bean.reader;

import java.io.InputStream;

/**
 * 对xml进行解析 得到BeanDefinition
 */
public interface XmlBeanDefinitionReader {
   void loadBeanDefinitions(InputStream inputStream);
}
