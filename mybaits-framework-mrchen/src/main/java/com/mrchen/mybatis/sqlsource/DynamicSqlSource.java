package com.mrchen.mybatis.sqlsource;

import com.mrchen.mybatis.sqlnode.DynamicContext;
import com.mrchen.mybatis.sqlnode.MixedSqlNode;
import com.mrchen.mybatis.sqlsource.SqlSourceParser;
import com.mrchen.mybatis.sqlnode.iface.SqlNode;
import com.mrchen.mybatis.sqlsource.iface.SqlSource;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-30 09:55
 */
public class DynamicSqlSource implements SqlSource {
    private SqlNode rootSqlNode;
    public DynamicSqlSource(MixedSqlNode rootSqlNode) {
      this.rootSqlNode=rootSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object paramObject) {
        DynamicContext context=new DynamicContext(paramObject);
        rootSqlNode.apply(context);

        String sql=context.getSql();

        SqlSourceParser sqlSourceParser=new SqlSourceParser();

        SqlSource sqlSource=sqlSourceParser.parse(sql);

        return sqlSource.getBoundSql(paramObject);
    }
}
