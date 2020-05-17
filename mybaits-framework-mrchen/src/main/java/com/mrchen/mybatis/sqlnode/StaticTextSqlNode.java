package com.mrchen.mybatis.sqlnode;

import com.mrchen.mybatis.sqlnode.iface.SqlNode;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-30 09:39
 */
public class StaticTextSqlNode implements SqlNode {
    private String sqlText;
    public StaticTextSqlNode(String sqlText) {
        this.sqlText=sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        context.appendSql(sqlText);
    }
}
