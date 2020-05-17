package com.mrchen.mybatis.config;

import com.mrchen.mybatis.io.Resources;
import com.mrchen.mybatis.utils.DocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-29 21:49
 */
public class XMLConfigBuilder {
    private Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parse(InputStream inputStream) {

        Document document= DocumentUtils.readDocument(inputStream);
        Element rootElement=document.getRootElement();
        parseConfiguration(rootElement);
        return configuration;
    }

    private void parseConfiguration(Element rootElement) {
        Element environmentElement=rootElement.element("environments");
        parseEnvironmentElement(environmentElement);

        Element mappersElement =rootElement.element("mappers");
        parseMappersElement(mappersElement);
    }

    private void parseMappersElement(Element mappersElement) {
        List<Element> mapperElements=mappersElement.elements("mapper");
        for (Element mapperElement : mapperElements){
            parseMapper(mapperElement);
        }
    }

    private void parseMapper(Element mapperElement) {
        String resource=mapperElement.attributeValue("resource");

        InputStream inputStream= Resources.getResourceAsStream(resource);
        Document document=DocumentUtils.readDocument(inputStream);
        //创建专门来解析映射文件的解析类
        XMLMapperBuilder mapperBuilder=new XMLMapperBuilder(configuration);
        mapperBuilder.parse(document.getRootElement());
    }

    private void parseEnvironmentElement(Element environmentElement) {
        String  defaultEnvId=environmentElement.attributeValue("default");
        if (defaultEnvId==null|| defaultEnvId.equals("")){
            return;
        }
        List<Element> elements=environmentElement.elements("environment");
        for (Element envElement:elements){
            String id= envElement.attributeValue("id");
            if (defaultEnvId.equals(id)){
                parseDataSource(envElement.element("dataSource"));
            }
        }
    }

    private void parseDataSource(Element dbElement) {
        String dbType=dbElement.attributeValue("type");
        if ("DBCP".equals(dbType)){
            BasicDataSource dataSource=new BasicDataSource();
            Properties properties=new Properties();
            List<Element> propertyElements=dbElement.elements();
            for (Element prop: propertyElements){
                String  name=prop.attributeValue("name");
                String value=prop.attributeValue("value");
                properties.put(name,value);
            }
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            configuration.setDataSource(dataSource);
        }
    }
}
