package com.mrchen.mybatis.sqlsession;

import com.mrchen.mybatis.config.Configuration;
import com.mrchen.mybatis.config.MappedStatement;
import com.mrchen.mybatis.executor.CachingExecutor;
import com.mrchen.mybatis.executor.SimpleExecutor;
import com.mrchen.mybatis.executor.iface.Executor;

import java.util.List;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-30 10:51
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;
    public DefaultSqlSession(Configuration configuration) {
        this.configuration=configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> list=this.selectList(statementId,param);
        if (list!=null && list.size()==1){
            return (T) list.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        MappedStatement mappedStatement=configuration.getMappedStatementById(statementId);
        Executor executor=new CachingExecutor(new SimpleExecutor());
        return executor.query(mappedStatement,configuration,param);
    }
}
