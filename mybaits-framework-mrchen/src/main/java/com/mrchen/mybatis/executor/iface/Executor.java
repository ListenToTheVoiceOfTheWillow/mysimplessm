package com.mrchen.mybatis.executor.iface;

import com.mrchen.mybatis.config.Configuration;
import com.mrchen.mybatis.config.MappedStatement;

import java.util.List;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-30 10:54
 */
public interface Executor {
    /**
     *
     * @param mappedStatement 获取SQL语句和入参出参类型信息
     * @param configuration 获取数据源连接处信息
     * @param param 获取入参类型
     * @param <T>
     * @return
     */
   <T> List<T> query(MappedStatement mappedStatement, Configuration configuration,Object param);
}
