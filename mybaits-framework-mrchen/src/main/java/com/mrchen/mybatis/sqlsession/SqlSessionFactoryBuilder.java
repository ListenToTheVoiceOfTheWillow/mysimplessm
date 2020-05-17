package com.mrchen.mybatis.sqlsession;

import com.mrchen.mybatis.config.Configuration;
import com.mrchen.mybatis.config.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-29 21:43
 */
public class SqlSessionFactoryBuilder {
    private Configuration configuration;
    public SqlSessionFactory build(InputStream inputStream){
        XMLConfigBuilder configBuilder=new XMLConfigBuilder();
        configuration=configBuilder.parse(inputStream);
        return build();
    }
    private SqlSessionFactory build(){
        return new DefaultSqlSessionFactory(configuration);
    }
}
