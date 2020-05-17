package com.mrchen.mybatis.sqlsource;

import com.mrchen.mybatis.sqlsource.iface.SqlSource;

import java.util.List;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-30 10:10
 */
public class StaticSqlSource implements SqlSource {
    private String sql;
    private List<ParameterMapping> parameterMappings;
    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings) {
          this.sql=sql;
          this.parameterMappings=parameterMappings;
    }

    @Override
    public BoundSql getBoundSql(Object paramObject) {
        return new BoundSql(sql,parameterMappings);
    }
}
