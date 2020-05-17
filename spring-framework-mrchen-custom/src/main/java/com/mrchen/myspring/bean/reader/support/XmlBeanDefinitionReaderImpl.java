package com.mrchen.myspring.bean.reader.support;

import com.mrchen.myspring.bean.reader.XmlBeanDefinitionDocumentReader;
import com.mrchen.myspring.bean.reader.XmlBeanDefinitionReader;
import com.mrchen.myspring.bean.registry.BeanDefinitionRegistry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.InputStream;

public class XmlBeanDefinitionReaderImpl implements XmlBeanDefinitionReader{
   private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReaderImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public void loadBeanDefinitions(InputStream inputStream) {
        SAXReader saxReader=new SAXReader();
        Document document=null;
        try {
            document= saxReader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        if (document==null){
            return;
        }
        Element rootElement=document.getRootElement();
        XmlBeanDefinitionDocumentReader xmlBeanDefinitionDocumentReader=new XmlBeanDefinitionDocumentReaderImpl(beanDefinitionRegistry);
        xmlBeanDefinitionDocumentReader.registerBeanDefinition(rootElement);
    }
}
