package com.mrchen.mybatis.config;

import com.mrchen.mybatis.nodehandler.NodeHandler;
import com.mrchen.mybatis.sqlnode.*;
import com.mrchen.mybatis.sqlnode.iface.SqlNode;
import com.mrchen.mybatis.sqlsource.DynamicSqlSource;
import com.mrchen.mybatis.sqlsource.RawSqlSource;
import com.mrchen.mybatis.sqlsource.iface.SqlSource;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-29 22:47
 */
public class XMLScriptParser {
    private boolean isDynamic = false;

    private Map<String,NodeHandler> nodeHandlerMap=new HashMap<>();

    public XMLScriptParser(){
        initNodeHandlers();
    }

    private void initNodeHandlers() {
       nodeHandlerMap.put("if",new IfNodeHandler());
    }


    public SqlSource parseScriptNode(Element selectElement) {
        // 解析script标签
        MixedSqlNode rootSqlNode=parseDynamicTags(selectElement);
        SqlSource sqlSource=null;
        if (isDynamic){
            sqlSource=new DynamicSqlSource(rootSqlNode);
        }else {
            sqlSource=new RawSqlSource(rootSqlNode);
        }
        return sqlSource;
    }

    private MixedSqlNode parseDynamicTags(Element selectElement) {
        List<SqlNode> contents=new ArrayList<>();

        int nodeCount=selectElement.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            //按照顺序获取每个节点对象
            Node node=selectElement.node(i);
            if (node instanceof Text){
                String sqlText=node.getText().trim();
                if (sqlText==null|| sqlText.equals("")){
                    continue;
                }
                TextSqlNode textSqlNode=new TextSqlNode(sqlText);
                if (textSqlNode.isDynamic()){
                    isDynamic=true;
                    contents.add(textSqlNode);
                }else {
                    contents.add(new StaticTextSqlNode(sqlText));
                }
            }else if (node instanceof Element){
                Element nodeToHandle= (Element) node;
                String name=nodeToHandle.getName();
                //怎么去查找相对应的标签处理器呢，需要通过标签名称
                NodeHandler nodeHandler=nodeHandlerMap.get(name);
                nodeHandler.handleNode(nodeToHandle,contents);
                isDynamic=true;
            }
        }
        return new MixedSqlNode(contents);
    }

    /**
     * 专门来解析if标签的标签处理器
     *
     * @author 灭霸詹
     *
     */
    class IfNodeHandler implements NodeHandler {

        /**
         * nodeToHandler：if标签
         */
        @Override
        public void handleNode(Element nodeToHandler, List<SqlNode> contents) {
            String test = nodeToHandler.attributeValue("test");

            MixedSqlNode parseDynamicTags = parseDynamicTags(nodeToHandler);

            contents.add(new IfSqlNode(test, parseDynamicTags));
        }

    }
}
