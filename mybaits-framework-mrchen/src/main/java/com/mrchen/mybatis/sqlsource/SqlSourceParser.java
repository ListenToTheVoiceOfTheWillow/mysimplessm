package com.mrchen.mybatis.sqlsource;

import com.mrchen.mybatis.sqlsource.StaticSqlSource;
import com.mrchen.mybatis.sqlsource.iface.SqlSource;
import com.mrchen.mybatis.utils.GenericTokenParser;
import com.mrchen.mybatis.utils.ParameterMappingTokenHandler;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-30 10:03
 */
public class SqlSourceParser {
    public SqlSource parse(String sql) {
        ParameterMappingTokenHandler tokenHandler=new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser=new GenericTokenParser("#{","}",tokenHandler);
        sql=tokenParser.parse(sql);
        return new StaticSqlSource(sql,tokenHandler.getParameterMappings());
    }
}
