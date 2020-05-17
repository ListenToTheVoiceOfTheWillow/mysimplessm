package com.mrchen.mybatis.config;

import org.dom4j.Element;

import java.util.List;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-29 22:30
 */
public class XMLMapperBuilder {
    private Configuration configuration;
    public XMLMapperBuilder(Configuration configuration) {
     this.configuration=configuration;
    }

    public void parse(Element rootElement) {
      //为了方便统一管理statement，需要使用statement唯一标识
        String namespace = rootElement.attributeValue("namespace");
   //     List<Element> selectElements=rootElement.elements("select");
        List<Element> selectElements=rootElement.elements();
        for (Element selectElement:selectElements){
            XMLStatementBuilder statementBuilder=new XMLStatementBuilder(configuration);
            statementBuilder.parseStatement(selectElement,namespace);
        }
    }
}
