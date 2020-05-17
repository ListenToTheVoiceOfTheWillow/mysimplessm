package com.mrchen.myspring.bean.reader;


import org.dom4j.Element;


public interface XmlBeanDefinitionDocumentReader {

    /**
     * 解析 注册beanBefinition
     * @param rootElement
     */
    void  registerBeanDefinition(Element rootElement);
}
