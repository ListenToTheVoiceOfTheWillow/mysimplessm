package com.mrchen.mybatis.config;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: mybatis-demo
 * @description: 封装了全局配置文件和映射文件中的信息
 * @author: mrchen
 * @create: 2020-04-29 21:45
 */
public class Configuration {
    private DataSource dataSource;
    private Map<String,MappedStatement> mappedStatementMap=new HashMap<String, MappedStatement>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void addMappedStatement(String statementId,MappedStatement mappedStatement){
        this.mappedStatementMap.put(statementId,mappedStatement);
    }
    public MappedStatement getMappedStatementById(String statementId){
        return this.mappedStatementMap.get(statementId);
    }
}
