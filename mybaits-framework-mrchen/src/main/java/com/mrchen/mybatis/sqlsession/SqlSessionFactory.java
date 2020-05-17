package com.mrchen.mybatis.sqlsession;

/**
 * @program: mybatis-demo
 * @description:主要是用来生产sqlsession的
 * @author: mrchen
 * @create: 2020-04-29 21:35
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
