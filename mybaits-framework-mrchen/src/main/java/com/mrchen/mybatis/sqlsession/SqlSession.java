package com.mrchen.mybatis.sqlsession;

import java.util.List;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-29 21:38
 */
public interface SqlSession {
    <T> T selectOne(String statementId, Object param);
    <T> List<T> selectList(String statementId, Object param);
}
