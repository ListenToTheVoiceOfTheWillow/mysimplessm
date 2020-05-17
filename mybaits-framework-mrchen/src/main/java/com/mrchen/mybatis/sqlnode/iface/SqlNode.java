package com.mrchen.mybatis.sqlnode.iface;

import com.mrchen.mybatis.sqlnode.DynamicContext;

/**
 * @program: mybatis-demo
 * @description: 封装不同的sql脚本，提供sql脚本处理功能
 * @author: mrchen
 * @create: 2020-04-30 09:27
 */
public interface SqlNode {
    void apply(DynamicContext context);
}
