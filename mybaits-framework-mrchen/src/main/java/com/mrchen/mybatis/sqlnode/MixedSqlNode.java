package com.mrchen.mybatis.sqlnode;

import com.mrchen.mybatis.sqlnode.iface.SqlNode;

import java.util.List;

/**
 * @program: mybatis-demo
 * @description:封装所有解析出来的sqlnode节点信息，方便统一管理
 * @author: mrchen
 * @create: 2020-04-30 09:26
 */
public class MixedSqlNode implements SqlNode {
    private List<SqlNode> sqlNodes;

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    @Override
    public void apply(DynamicContext context) {
        for (SqlNode sqlNode:sqlNodes){
            sqlNode.apply(context);
        }
    }
}
