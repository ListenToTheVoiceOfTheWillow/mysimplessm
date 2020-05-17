package com.mrchen.mybatis.executor;

import com.mrchen.mybatis.config.Configuration;
import com.mrchen.mybatis.config.MappedStatement;
import com.mrchen.mybatis.executor.iface.Executor;

import java.util.List;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-30 11:02
 */
public class CachingExecutor implements Executor {
    private Executor delegate;

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
        return delegate.query(mappedStatement,configuration,param);
    }
}
