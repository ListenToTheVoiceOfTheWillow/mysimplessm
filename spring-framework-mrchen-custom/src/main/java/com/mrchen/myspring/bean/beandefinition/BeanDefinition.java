package com.mrchen.myspring.bean.beandefinition;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {
     private String beanName;
     private String className;
     private String scope;
     private String initMethod;
     private Class<?> clazz;
     private List<PropertyValue> propertyValues=new ArrayList<>();

     public void addPropertyValues(PropertyValue propertyValue){
         propertyValues.add(propertyValue);
     }
    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }


    public void setPropertyValues(List<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

   public static class Build{
        private  BeanDefinition beanDefinition=new BeanDefinition();

       public Build name(String beanName){
            this.beanDefinition.beanName=beanName;
            return this;
        }

        public Build className(String className){
           this.beanDefinition.className=className;
           return this;
        }

       public Build scope(String scope){
            this.beanDefinition.scope=scope;
            return this;
        }

       public Build initMethod(String initMethod){
            this.beanDefinition.initMethod=initMethod;
            return this;
        }
       public Build clazz(Class<?> clazz){
           this.beanDefinition.clazz=clazz;
           return this;
       }
       public BeanDefinition build(){
            return beanDefinition;
        }

    }
}
