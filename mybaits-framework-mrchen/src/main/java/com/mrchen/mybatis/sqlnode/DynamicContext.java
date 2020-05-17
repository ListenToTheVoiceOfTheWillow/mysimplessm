package com.mrchen.mybatis.sqlnode;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: mybatis-demo
 * @description:封装了sql信息，可以封装入参信息
 * @author: mrchen
 * @create: 2020-04-30 09:29
 */
public class DynamicContext {
    private StringBuffer sb=new StringBuffer();
    private Map<String,Object> bindings=new HashMap<>();

    public DynamicContext(Object parameter){
        this.bindings.put("_parameter",parameter);
    }
    public String getSql(){
        return sb.toString();
    }
    public void appendSql(String sql){
        sb.append(sql);
        sb.append(" ");
    }
    public Map<String, Object> getBindings() {
        return bindings;
    }
}
