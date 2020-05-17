package com.mrchen.testspring.testmybatis;


import com.mrchen.mybatis.config.Configuration;
import com.mrchen.mybatis.config.XMLConfigBuilder;
import com.mrchen.mybatis.io.Resources;
import com.mrchen.mybatis.sqlsession.SqlSession;
import com.mrchen.mybatis.sqlsession.SqlSessionFactory;
import com.mrchen.mybatis.sqlsession.SqlSessionFactoryBuilder;
import com.mrchen.testspring.po.User;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserDaoTest {



	@Test
	public void testQueryUserById2() {
		// 调用解析流程
		// 1.指定全局配置文件路径
		String location = "mybatis-config.xml";
		// 2.加载配置文件成InputStream
		InputStream inputStream = Resources.getResourceAsStream(location);

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession sqlSession = sqlSessionFactory.openSession();

		User param = new User();
		param.setId(2);
       // param.setUsername("王鹏");
		// 映射文件中的statementId，由namespace和statementId组成
		String statementId = "test.findUserById";

		List<User> users = sqlSession.selectList(statementId, param);

		System.out.println(users.toString());
	}

}
