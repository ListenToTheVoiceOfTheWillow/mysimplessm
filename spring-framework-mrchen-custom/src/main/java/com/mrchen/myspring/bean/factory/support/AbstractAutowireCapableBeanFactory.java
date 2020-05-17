package com.mrchen.myspring.bean.factory.support;

import com.mrchen.myspring.bean.aware.iface.Aware;
import com.mrchen.myspring.bean.aware.iface.BeanFactoryAware;
import com.mrchen.myspring.bean.beandefinition.BeanDefinition;
import com.mrchen.myspring.bean.beandefinition.PropertyValue;
import com.mrchen.myspring.bean.beandefinition.RunReferenceValue;
import com.mrchen.myspring.bean.beandefinition.TypeStringValue;
import com.mrchen.myspring.bean.factory.AutowireCapableBeanFactory;
import com.mrchen.myspring.bean.utils.ReflectUtils;

import java.util.List;

public abstract class AbstractAutowireCapableBeanFactory extends AbastractBeanFactory implements AutowireCapableBeanFactory{
    @Override
    public Object createBean(BeanDefinition beanDefinition) {
        //创建实例
          Class<?> clazz=beanDefinition.getClazz();
          if (clazz==null){
              return null;
          }
          Object singleton=createInstance(beanDefinition);
        //属性填充
          populate(singleton,beanDefinition);
        //调用初始化方法
        initBean(beanDefinition,singleton);
        return singleton;
    }

    private  void initBean(BeanDefinition beanDefinition, Object singleton){
        if (singleton instanceof Aware){
            if (singleton instanceof BeanFactoryAware){
                    ((BeanFactoryAware) singleton).setBeanFactory(this);
            }
        }
        // TODO BeanPostProcessor的前置方法执行
        initMethod(singleton ,beanDefinition);
        // TODO BeanPostProcessor的后置方法执行（AOP代理对象产生的入口）
    }

    private void initMethod(Object singleton, BeanDefinition beanDefinition) {
        // TODO 完成InitializingBean接口（标记接口）的处理，调用的是afterPropertySet方法

        // 完成init-metho标签属性对应的方法调用
        if (beanDefinition.getInitMethod()==null){
            return;
        }
        ReflectUtils.invokeMethod(singleton, beanDefinition.getInitMethod());
    }

    private void populate(Object singleton, BeanDefinition beanDefinition){
        List<PropertyValue> propertyValues=beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue:propertyValues){
            String name=propertyValue.getName();
            Object value=propertyValue.getValue();

            Object valueToUse=null;
            if (value instanceof TypeStringValue){
                TypeStringValue typeStringValue= (TypeStringValue) value;
                Class<?> clazz=typeStringValue.getClazz();
                  if (clazz==Integer.class){
                      valueToUse=Integer.parseInt(typeStringValue.getStringValue());
                  }else if (clazz== String.class){
                      valueToUse=typeStringValue.getStringValue();
                  }else {
                      //...
                  }

            }else if (value instanceof RunReferenceValue){
                RunReferenceValue runReferenceValue= (RunReferenceValue) value;
                String ref=runReferenceValue.getRef();
                valueToUse= getBean(ref);

            }else {
                //...
            }
            ReflectUtils.setProperty(singleton,name,valueToUse);


        }
    }

    private Object createInstance(BeanDefinition beanDefinition){
        Object bean= ReflectUtils.createObject(beanDefinition.getClazz());
        return bean;
    }


}
