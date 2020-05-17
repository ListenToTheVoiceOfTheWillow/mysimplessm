package com.mrchen.myspring.bean.reader.support;

import com.mrchen.myspring.bean.beandefinition.BeanDefinition;
import com.mrchen.myspring.bean.beandefinition.PropertyValue;
import com.mrchen.myspring.bean.beandefinition.RunReferenceValue;
import com.mrchen.myspring.bean.beandefinition.TypeStringValue;
import com.mrchen.myspring.bean.reader.XmlBeanDefinitionDocumentReader;
import com.mrchen.myspring.bean.registry.BeanDefinitionRegistry;

import com.mrchen.myspring.bean.utils.ReflectUtils;
import org.dom4j.Element;

import java.util.List;


public class XmlBeanDefinitionDocumentReaderImpl implements XmlBeanDefinitionDocumentReader{
    BeanDefinitionRegistry beanDefinitionRegistery;
    public XmlBeanDefinitionDocumentReaderImpl(BeanDefinitionRegistry beanDefinitionRegistery) {
        this.beanDefinitionRegistery = beanDefinitionRegistery;
    }


    @Override
    public void registerBeanDefinition(Element rootElement) {
        List<Element> beanElements=rootElement.elements();
        for (Element beanElement:beanElements){
            if ("bean".equals(beanElement.getName())){
                parseDefaultElement(beanElement);
            }else {
                parseCustomerElement(beanElement);
            }
        }
    }

    /**
     * 解析默认标签
     * @param beanElement
     */
    private void parseDefaultElement(Element beanElement) {
          String id=beanElement.attributeValue("id");
          String name=beanElement.attributeValue("name");
          String beanName=id==null?name:id;
          String className=beanElement.attributeValue("class");

         Class<?> clazz=null;
        try {
            clazz=Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (beanName==null){
            beanName=clazz.getSimpleName();
        }
        String scope=beanElement.attributeValue("scope");
          scope=scope==null?"singleton":scope;
          String initMethod=beanElement.attributeValue("init-method");

          BeanDefinition beanDefinition=  new BeanDefinition.Build().name(beanName).className(className).scope(scope).initMethod(initMethod).clazz(clazz).build();
          //解析property
          List<Element> propertyElements=beanElement.elements();
          for (Element propertyElement:propertyElements){
               parsePropertyElement(beanDefinition,propertyElement);
          }

          beanDefinitionRegistery.registerBeanDefinition(beanName,beanDefinition);

    }

    private void parsePropertyElement(BeanDefinition beanDefinition, Element propertyElement) {
           String name=propertyElement.attributeValue("name");
           String value=propertyElement.attributeValue("value");
           String ref=propertyElement.attributeValue("ref");
           if (name!=null&&value!=null&&ref!=null){
               return;
           }
         PropertyValue propertyValue=new PropertyValue();
           if (name!=null&&value!=null){
               Class<?> clazz= ReflectUtils.getTypeByFieldName(beanDefinition.getClazz(),name);
               if (clazz==null){
                   return;
               }
               TypeStringValue typeStringValue=new TypeStringValue();
               typeStringValue.setClazz(clazz);
               typeStringValue.setStringValue(value);
               propertyValue.setName(name);
               propertyValue.setValue(typeStringValue);
               beanDefinition.addPropertyValues(propertyValue);
           }else if (name!=null&&ref!=null){
               RunReferenceValue runReferenceValue=new RunReferenceValue();
               runReferenceValue.setRef(ref);
               propertyValue.setName(name);
               propertyValue.setValue(runReferenceValue);
               beanDefinition.addPropertyValues(propertyValue);
           }else {
               return;
           }
    }

    /**
     * 解析自定义标签
     * @param beanElement
     */
    private void parseCustomerElement(Element beanElement) {
      //TODO 解析自定义标签
    }
}
