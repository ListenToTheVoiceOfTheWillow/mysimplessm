package com.mrchen.mybatis.sqlsource.iface;

import com.mrchen.mybatis.sqlsource.BoundSql;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-29 22:43
 */
public interface SqlSource {
    BoundSql getBoundSql(Object paramObject);
}
