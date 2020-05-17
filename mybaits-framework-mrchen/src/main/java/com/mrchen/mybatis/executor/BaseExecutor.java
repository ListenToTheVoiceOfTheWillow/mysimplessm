package com.mrchen.mybatis.executor;

import com.mrchen.mybatis.config.Configuration;
import com.mrchen.mybatis.config.MappedStatement;
import com.mrchen.mybatis.executor.iface.Executor;
import com.mrchen.mybatis.sqlsource.BoundSql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-30 10:57
 */
public abstract class BaseExecutor implements Executor {
    private Map<String, List<Object>> oneLevelCache=new HashMap<>();

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
        BoundSql boundSql=mappedStatement.getSqlSource().getBoundSql(param);
        List<Object> results=oneLevelCache.get(boundSql.getSql());
        if (results!=null){
            return (List<T>) results;
        }
        //查询数据库
        results=queryFromDataBase(mappedStatement,configuration,param,boundSql);
        oneLevelCache.put(boundSql.getSql(),results);
        return (List<T>) results;
    }

    protected abstract List<Object> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration, Object param, BoundSql boundSql);
}
