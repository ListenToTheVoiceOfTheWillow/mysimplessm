package com.mrchen.mybatis.io;

import java.io.InputStream;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-29 21:32
 */
public class Resources {
      public static InputStream getResourceAsStream(String location){
          return Resources.class.getClassLoader().getResourceAsStream(location);
      }
}
