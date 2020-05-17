package com.mrchen.mybatis.sqlsource;

import com.mrchen.mybatis.sqlnode.DynamicContext;
import com.mrchen.mybatis.sqlnode.MixedSqlNode;
import com.mrchen.mybatis.sqlsource.SqlSourceParser;
import com.mrchen.mybatis.sqlsource.iface.SqlSource;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-30 09:55
 */
public class RawSqlSource implements SqlSource {
    private SqlSource sqlSource;
    public RawSqlSource(MixedSqlNode rootSqlNode) {
      DynamicContext context=new DynamicContext(null);
      //将SqlNode处理成一条sql语句
        rootSqlNode.apply(context);
        //该sql语句，此时还包含#{}，不包括${}
        String sql=context.getSql();
        //通过SqlSourceParse去解析SqlSource中的#{}
        SqlSourceParser sqlSourceParser=new SqlSourceParser();
        //将解析的结果，最终封装成staticSqlSource
        sqlSource=sqlSourceParser.parse(sql);
    }

    @Override
    public BoundSql getBoundSql(Object paramObject) {
        return sqlSource.getBoundSql(paramObject);
    }
}
