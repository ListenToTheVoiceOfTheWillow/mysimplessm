package com.mrchen.mybatis.sqlnode;

import com.mrchen.mybatis.sqlnode.iface.SqlNode;
import com.mrchen.mybatis.utils.OgnlUtils;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-30 09:48
 */
public class IfSqlNode implements SqlNode {
    private String test;
    private SqlNode rootSqlNode;
    public IfSqlNode(String test, MixedSqlNode rootSqlNode) {
       this.test=test;
       this.rootSqlNode=rootSqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        boolean testValue= OgnlUtils.evaluateBoolean(test,context.getBindings().get("_parameter"));
        if (testValue){
            rootSqlNode.apply(context);
        }
    }
}
