package com.mrchen.mybatis.nodehandler;


import com.mrchen.mybatis.sqlnode.iface.SqlNode;
import org.dom4j.Element;

import java.util.List;

/**
 * @program: mybatis-demo
 * @description:处理select标签的子标签
 * @author: mrchen
 * @create: 2020-04-30 09:43
 */
public interface NodeHandler {
   void handleNode(Element nodeToHandle, List<SqlNode> contents);
}
