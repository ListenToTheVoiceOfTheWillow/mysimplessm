package com.mrchen.mybatis.sqlsession;

import com.mrchen.mybatis.config.Configuration;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-29 21:51
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSession openSession() {
      // 开启session
        return new DefaultSqlSession(configuration);
    }
}
