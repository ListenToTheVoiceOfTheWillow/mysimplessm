package com.mrchen.mybatis.config;

import com.mrchen.mybatis.sqlsource.iface.SqlSource;
import org.dom4j.Element;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-29 22:34
 */
public class XMLStatementBuilder {
    private Configuration configuration;

    public XMLStatementBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseStatement(Element selectElement, String namespace) {
        String statementId= selectElement.attributeValue("id");
        if (statementId ==null|| selectElement.equals("")){
            return;
        }
        statementId=namespace+"."+statementId;

        String parameterType=selectElement.attributeValue("parameterType");
        Class<?> parameterClass=resolveType(parameterType);

        String resultType=selectElement.attributeValue("resultType");
        Class<?> resultClass=resolveType(resultType);

        String statementType=selectElement.attributeValue("statementType");
        statementType=statementType==null||statementType.equals("")?"prepared":statementType;

        SqlSource sqlSource=createSqlSource(selectElement);

        //解析Sql信息
        MappedStatement mappedStatement= new MappedStatement(statementId,parameterClass,resultClass,statementType,sqlSource);
        configuration.addMappedStatement(statementId,mappedStatement);
    }

    private SqlSource createSqlSource(Element selectElement) {
        XMLScriptParser scriptParser=new XMLScriptParser();
        SqlSource sqlSource=scriptParser.parseScriptNode(selectElement);
        return  sqlSource;
    }

    private Class<?> resolveType(String parameterType) {
        try {
            Class<?> clazz = Class.forName(parameterType);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
